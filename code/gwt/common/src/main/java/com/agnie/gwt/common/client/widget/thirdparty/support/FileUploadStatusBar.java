package com.agnie.gwt.common.client.widget.thirdparty.support;

import gwtupload.client.HasProgress;
import gwtupload.client.IUploadStatus;
import gwtupload.client.IUploader;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * To show file upload status and also progress of it, with file unloaders from module gwtupload.GWTUpload
 * 
 */
public class FileUploadStatusBar extends Composite implements IUploadStatus {

	private static StatusBarResources	res	= StatusBarResources.INSTANCE;
	static {
		res.css().ensureInjected();
	}

	public class ProgressBar extends Composite implements HasProgress {

		private HTMLPanel	panel		= new HTMLPanel("");
		HTMLPanel			progress	= new HTMLPanel("");
		HTML				progressMsg	= new HTML();

		public ProgressBar() {
			progress.add(progressMsg);
			panel.add(progress);
			panel.addStyleName(res.css().progress());
			progress.addStyleName(res.css().progressBar());
			progress.setWidth("0px");
			initWidget(panel);
		}

		public void setProgress(int done, int total) {
			int percent = IUploader.Utils.getPercent(done, total);
			progress.setWidth(percent + "%");
			progressMsg.setText(percent + "%");
		}
	}

	/**
	 * Cancel button.
	 */
	@UiField
	protected SpanElement				statusLabel;

	/**
	 * Label with the progress status.
	 */
	@UiField
	protected Anchor					cancel;
	/**
	 * Main panel, attach it to the document using getWidget().
	 */
	protected HTMLPanel					panel;

	@UiField
	protected SimplePanel				progressBarContainer;

	protected Set<CancelBehavior>		cancelCfg						= DEFAULT_CANCEL_CFG;
	private boolean						hasCancelActions				= false;

	private UploadStatusConstants		i18nStrs						= GWT.create(UploadStatusConstants.class);
	private UploadStatusChangedHandler	onUploadStatusChangedHandler	= null;
	private Widget						prg								= null;
	private IUploadStatus.Status		status							= Status.UNINITIALIZED;

	interface MyUiBinder extends UiBinder<Widget, FileUploadStatusBar> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	/**
	 * Default Constructor.
	 */
	public FileUploadStatusBar() {
		this(res.css().fileUpload());
	}

	public FileUploadStatusBar(String styleName) {
		panel = (HTMLPanel) uiBinder.createAndBindUi(this);
		panel.addStyleName(styleName);
		initWidget(panel);
		cancel.setHref("");
	}

	public HandlerRegistration addCancelHandler(final UploadCancelHandler handler) {
		hasCancelActions = true;
		return cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handler.onCancel();
			}
		});
	}

	public Status getStatus() {
		return status;
	}

	public Widget getWidget() {
		return this;
	}

	@Override
	public IUploadStatus newInstance() {
		IUploadStatus ret = new FileUploadStatusBar();
		ret.setCancelConfiguration(cancelCfg);
		return ret;
	}

	public void setCancelConfiguration(Set<CancelBehavior> config) {
		cancelCfg = config;
	}

	public void setError(String msg) {
		setStatus(Status.ERROR);
		Window.alert(msg.replaceAll("\\\\n", "\\n"));
	}

	@Override
	public void setFileName(String name) {

	}

	@Override
	public void setI18Constants(UploadStatusConstants strs) {
		i18nStrs = strs;
	}

	@Override
	public void setStatus(Status stat) {
		String statusName = stat.toString().toLowerCase();
		statusLabel.setClassName(statusName);
		switch (stat) {
		case CHANGED:
		case QUEUED:
			updateStatusPanel(false, i18nStrs.uploadStatusQueued());
			break;
		case SUBMITING:
			updateStatusPanel(false, i18nStrs.uploadStatusSubmitting());
			break;
		case INPROGRESS:
			updateStatusPanel(true, i18nStrs.uploadStatusInProgress());
			if (!cancelCfg.contains(CancelBehavior.STOP_CURRENT)) {
				cancel.setVisible(false);
			}
			break;
		case SUCCESS:
		case REPEATED:
			updateStatusPanel(false, i18nStrs.uploadStatusSuccess());
			if (!cancelCfg.contains(CancelBehavior.REMOVE_REMOTE)) {
				cancel.setVisible(false);
			}
			break;
		case INVALID:
			getWidget().removeFromParent();
			break;
		case CANCELING:
			updateStatusPanel(false, i18nStrs.uploadStatusCanceling());
			break;
		case CANCELED:
			updateStatusPanel(false, i18nStrs.uploadStatusCanceled());
			if (cancelCfg.contains(CancelBehavior.REMOVE_CANCELLED_FROM_LIST)) {
				getWidget().removeFromParent();
			}
			break;
		case ERROR:
			updateStatusPanel(false, i18nStrs.uploadStatusError());
			break;
		case DELETED:
			updateStatusPanel(false, i18nStrs.uploadStatusDeleted());
			getWidget().removeFromParent();
			break;
		}
		if (status != stat && onUploadStatusChangedHandler != null) {
			status = stat;
			onUploadStatusChangedHandler.onStatusChanged(this);
		}
		status = stat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setProgress(int, int)
	 */
	public void setProgress(int done, int total) {
		if (prg != null) {
			if (prg instanceof HasProgress) {
				((HasProgress) prg).setProgress(done, total);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gwtupload.client.IUploadStatus#addStatusChangedHandler(gwtupload.client.IUploadStatus.UploadStatusChangedHandler)
	 */
	public void setStatusChangedHandler(final UploadStatusChangedHandler handler) {
		onUploadStatusChangedHandler = handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setVisible(boolean)
	 */
	public void setVisible(boolean b) {
		panel.setVisible(b);
	}

	/**
	 * Override the default progress widget with a customizable one.
	 * 
	 * @param progress
	 */
	protected void setProgressWidget(Widget progress) {
		if (prg != null) {
			progressBarContainer.remove(prg);
		}
		prg = progress;
		progressBarContainer.add(prg);
		prg.setVisible(false);
	}

	protected void setShowStatusLabel(boolean showProgress) {
		if (showProgress) {
			statusLabel.setAttribute("style", "");
		} else {
			statusLabel.setAttribute("style", "display: none");
		}
	}

	/**
	 * Thought to be overridable by the user when extending this.
	 * 
	 * @param showProgress
	 * @param message
	 */
	protected void updateStatusPanel(boolean showProgress, String message) {
		if (showProgress && prg == null) {
			setProgressWidget(new ProgressBar());
		}
		if (prg != null) {
			prg.setVisible(showProgress);
		}
		setShowStatusLabel(showProgress);
		statusLabel.setInnerText(message);
		cancel.setVisible(hasCancelActions && !cancelCfg.contains(CancelBehavior.DISABLED));
	}
}
