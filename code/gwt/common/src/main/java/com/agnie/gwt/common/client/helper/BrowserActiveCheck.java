package com.agnie.gwt.common.client.helper;

import com.agnie.gwt.common.client.mvp.MainView;
import com.google.inject.Singleton;

@Singleton
public class BrowserActiveCheck {

    private MainView mainView;

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public BrowserActiveCheck() {
        // TODO Auto-generated constructor stub
    }

    // var maninview = this.com.agnie.gwt.common.client.helper.BrowserActiveCheck::mainView;
    public native void setActiveListner() /*-{
                                          // Set the name of the hidden property and the change event for visibility
                                          var hidden, visibilityChange; 
                                          if (typeof document.hidden !== "undefined") { // Opera 12.10 and Firefox 18 and later support 
                                          hidden = "hidden";
                                          visibilityChange = "visibilitychange";
                                          } else if (typeof document.mozHidden !== "undefined") {
                                          hidden = "mozHidden";
                                          visibilityChange = "mozvisibilitychange";
                                          } else if (typeof document.msHidden !== "undefined") {
                                          hidden = "msHidden";
                                          visibilityChange = "msvisibilitychange";
                                          } else if (typeof document.webkitHidden !== "undefined") {
                                          hidden = "webkitHidden";
                                          visibilityChange = "webkitvisibilitychange";
                                          }
                                          // If the page is hidden, pause the video;
                                          // if the page is shown, play the video
                                          var that = this;
                                          function handleVisibilityChange() {
                                          var valueInString;
                                                  if ($doc[hidden]) {
                                                  // pause State
                                                   valueInString = "false";
                                          //       console.log("Pause State");
                                                  } else {
                                                  // play state
                                                   valueInString = "true";
                                          //      console.log("Play State");
                                                  }
                                                  that.@com.agnie.gwt.common.client.helper.BrowserActiveCheck::changeSate(Ljava/lang/String;)(valueInString);
                                          }
                                          // Warn if the browser doesn't support addEventListener or the Page Visibility API
                                          if (typeof $doc.addEventListener === "undefined" || 
                                          typeof $doc[hidden] === "undefined") {
                                          console.log("This demo requires a browser, such as Google Chrome or Firefox, that supports the Page Visibility API.");
                                          } else {
                                          // Handle page visibility change   
                                          $doc.addEventListener(visibilityChange, handleVisibilityChange, false);
                                          }
                                            }-*/;

    public void changeSate(String state) {
        if (this.mainView != null) {
            if (state.equals("true"))
                this.mainView.setBrowserTabActive(true);
            else
                this.mainView.setBrowserTabActive(false);
        }
    }
}
