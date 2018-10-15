package com.egrasoft.commentremover.util;

import com.egrasoft.commentremover.service.LocalizationService;
import com.egrasoft.fxcommons.util.MessageHelper;

public class CommentRemoverMessageHelper implements MessageHelper {
    private LocalizationService localizationService = LocalizationService.getInstance();

    @Override
    public String getSaveFileDialogTitle() {
        return message(Constants.Dialogs.FILE_SAVE_TITLE_KEY);
    }

    @Override
    public String getSaveFileErrorDialogTitle() {
        return message(Constants.Dialogs.FILE_ERROR_TITLE_KEY);
    }

    @Override
    public String getSaveFileErrorDialogText() {
        return message(Constants.Dialogs.FILE_SAVE_ERROR_CONTENT_TEXT_KEY);
    }

    @Override
    public String getOpenFileDialogTitle() {
        return message(Constants.Dialogs.FILE_OPEN_TITLE_KEY);
    }

    @Override
    public String getOpenFileErrorDialogTitle() {
        return message(Constants.Dialogs.FILE_ERROR_TITLE_KEY);
    }

    @Override
    public String getOpenFileErrorDialogText() {
        return message(Constants.Dialogs.FILE_OPEN_ERROR_CONTENT_TEXT_KEY);
    }

    @Override
    public String getAskForSavingDialogTitle() {
        return message(Constants.Dialogs.ASK_FOR_SAVING_TITLE_KEY);
    }

    @Override
    public String getAskForSavingDialogText() {
        return message(Constants.Dialogs.ASK_FOR_SAVING_CONTENT_TEXT_KEY);
    }

    @Override
    public String getAskForSavingOptionYes() {
        return message(Constants.Dialogs.ASK_FOR_SAVING_SAVE_KEY);
    }

    @Override
    public String getAskForSavingOptionNo() {
        return message(Constants.Dialogs.ASK_FOR_SAVING_DONT_SAVE_KEY);
    }

    @Override
    public String getAskForSavingOptionCancel() {
        return message(Constants.Dialogs.ASK_FOR_SAVING_CANCEL_KEY);
    }

    private String message(String key) {
        return localizationService.getString(key);
    }
}
