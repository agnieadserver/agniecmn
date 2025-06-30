#!/bin/bash

M2_REPO=/media/pandurang/Primary-Data/m2-repository
GITHUB_REPO_URL=https://maven.pkg.github.com/agnieadserver/adserver
REPO_ID=github

echo "📦 Scanning $M2_REPO for artifacts..."

find "$M2_REPO" -name "*.pom" | while read -r pom; do
  dir=$(dirname "$pom")
  base=$(basename "$pom" .pom)
  jar="$dir/$base.jar"

  # Extract groupId, artifactId, version
  relative_path="${pom#$M2_REPO/}"
  IFS='/' read -r -a parts <<< "$relative_path"
  len=${#parts[@]}

  version=${parts[$len-2]}
  artifactId=${parts[$len-3]}
  groupId=$(IFS=.; printf "%s." "${parts[@]:0:$len-3}")
  groupId=${groupId%.}

  echo "🚀 Processing $groupId:$artifactId:$version"

  if [[ -f "$jar" ]]; then
    packaging="jar"
    file="-Dfile=$jar"
  else
    packaging="pom"
    file="-Dfile=$pom"
  fi

  mvn deploy:deploy-file \
    -DgroupId="$groupId" \
    -DartifactId="$artifactId" \
    -Dversion="$version" \
    -DpomFile="$pom" \
    -Dpackaging="$packaging" \
    $file \
    -DrepositoryId="$REPO_ID" \
    -Durl="$GITHUB_REPO_URL" \
    -DgeneratePom=false \
    -DretryFailedDeploymentCount=3 \
    -q

  if [[ $? -ne 0 ]]; then
    echo "❌ Failed to deploy $groupId:$artifactId:$version"
  else
    echo "✅ Deployed $groupId:$artifactId:$version"
  fi
done
