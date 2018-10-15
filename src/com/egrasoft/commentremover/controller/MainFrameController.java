package com.egrasoft.commentremover.controller;

import com.egrasoft.commentremover.service.LocalizationService;
import com.egrasoft.commentremover.service.PascalFileManagerService;
import com.egrasoft.commentremover.service.RegexpService;
import com.egrasoft.commentremover.util.CommentRemoverMessageHelper;
import com.egrasoft.commentremover.util.Constants;
import com.egrasoft.fxcommons.controller.FileWorkFrameController;
import com.egrasoft.fxcommons.service.FileManagerService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;

import static com.egrasoft.fxcommons.util.ControllerUtils.createMessageDialog;

public class MainFrameController extends FileWorkFrameController<String> {
    private LocalizationService localizationService = LocalizationService.getInstance();
    private RegexpService regexpService = RegexpService.getInstance();

    private Stage stage;

    @FXML
    private TextArea textArea;

    public MainFrameController(Stage stage) {
        super(stage, new CommentRemoverMessageHelper());
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            markChanged(true);
            updateTitle();
        });
    }

    @FXML
    private void doFileNew() {
        super.newFile();
    }

    @FXML
    private void doFileOpen() {
        super.openFile();
    }

    @FXML
    private void doFileSave() {
        super.save();
    }

    @FXML
    private void doFileSaveAs() {
        super.saveAs();
    }

    @FXML
    private void doFileClose() {
        super.closeFile();
    }

    @FXML
    private void doQuit() {
        super.closeWindow();
    }

    @FXML
    private void doRemoveComments() {
        textArea.setText(regexpService.removeComments(getCurrentData()));
    }

    @FXML
    private void doAbout() {
        createMessageDialog(Alert.AlertType.INFORMATION,
                localizationService.getString(Constants.Dialogs.ABOUT_TITLE_KEY),
                localizationService.getString(Constants.Dialogs.ABOUT_CONTENT_TEXT_KEY)).showAndWait();
    }

    @Override
    protected FileManagerService<String> getFileManagerService() {
        return PascalFileManagerService.getInstance();
    }

    @Override
    protected String getCurrentData() {
        return textArea.getText();
    }

    @Override
    protected void onFileSaved() {
        updateTitle();
    }

    @Override
    protected void onFileOpened(String s) {
        textArea.setText(s);
        textArea.setVisible(true);
        updateTitle();
    }

    @Override
    protected void onFileClosed() {
        textArea.clear();
        textArea.setVisible(false);
        updateTitle();
    }

    @Override
    protected void onFileNew() {
        textArea.clear();
        textArea.setVisible(true);
        updateTitle();
    }

    private void updateTitle() {
        String title = localizationService.getString(Constants.Frame.MAIN_FRAME_TITLE_KEY);
        if (hasFile()) {
            File currentFile = getCurrentFile();
            String fileName = currentFile == null ?
                    localizationService.getString(Constants.Misc.ANONYMOUS_FILE_NAME_KEY) : currentFile.getName();
            title += " (" + fileName + ")";
            if (hasUnsavedChanges()) {
                title += " *";
            }
        }
        stage.setTitle(title);
    }
}
