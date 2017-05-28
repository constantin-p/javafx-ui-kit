package ui.control;


import com.sun.javafx.scene.control.behavior.TextFieldBehavior;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/*
 * TextFieldSkin API from:
 *  http://grepcode.com/file/repo1.maven.org/maven2/net.java.openjfx.backport/openjfx-78-backport/1.8.0-ea-b96.1/com/sun/javafx/scene/control/skin/TextFieldSkin.java
 *  http://grepcode.com/file/repo1.maven.org/maven2/net.java.openjfx.backport/openjfx-78-backport/1.8.0-ea-b96.1/com/sun/javafx/scene/control/skin/BehaviorSkinBase.java
 */
public abstract class CPDecoratedTextFieldSkin extends TextFieldSkin {
    public final static String STYLE_CLASS = "cp-decorated-text-field";
    public final static String LEFT_DECORATION_WRAPPER_STYLE_CLASS = "cp-left-stack-pane";
    public final static String RIGHT_DECORATION_WRAPPER_STYLE_CLASS = "cp-right-stack-pane";

    private Node leftDecorationNode;
    private StackPane leftDecorationStackPane;
    private Node rightDecorationNode;
    private StackPane rightDecorationStackPane;

    private final TextField textField;

    public CPDecoratedTextFieldSkin(TextField textField) {
        super(textField, new TextFieldBehavior(textField));

        this.textField = textField;

        // Initial update
        updateLeftDecoration();
        updateRightDecoration();

        // Register for updates (handled in handleControlPropertyChanged)
        registerChangeListener(leftDecorationProperty(), "leftDecoration");
        registerChangeListener(rightDecorationProperty(), "rightDecoration");

        this.textField.getStyleClass().add(STYLE_CLASS);
    }

    public abstract ObjectProperty<Node> leftDecorationProperty();
    public abstract ObjectProperty<Node> rightDecorationProperty();

    @Override
    protected void handleControlPropertyChanged(String propertyReference) {
        super.handleControlPropertyChanged(propertyReference);

        if (propertyReference.equals("leftDecoration")) {
            updateLeftDecoration();
        } else if (propertyReference.equals("rightDecoration")) {
            updateRightDecoration();
        }
    }

    private void updateLeftDecoration() {
        final Node newValue = leftDecorationProperty().get();
        if (newValue != null && !newValue.equals(leftDecorationNode)) {
            if (leftDecorationStackPane == null) {
                leftDecorationStackPane = new StackPane();
                leftDecorationStackPane.setAlignment(Pos.CENTER_LEFT);
                leftDecorationStackPane.getStyleClass().add(LEFT_DECORATION_WRAPPER_STYLE_CLASS);
                getChildren().add(leftDecorationStackPane);
            } else if (leftDecorationNode != null) {
                leftDecorationStackPane.getChildren().remove(leftDecorationNode);
            }
            leftDecorationStackPane.getChildren().add(newValue);
            leftDecorationNode = newValue;
        }
    }

    private void updateRightDecoration() {
        final Node newValue = rightDecorationProperty().get();
        if (newValue != null && !newValue.equals(rightDecorationNode)) {
            if (rightDecorationStackPane == null) {
                rightDecorationStackPane = new StackPane();
                rightDecorationStackPane.setAlignment(Pos.CENTER_RIGHT);
                rightDecorationStackPane.getStyleClass().add(RIGHT_DECORATION_WRAPPER_STYLE_CLASS);
                getChildren().add(rightDecorationStackPane);
            } else if (rightDecorationNode != null) {
                rightDecorationStackPane.getChildren().remove(rightDecorationNode);
            }
            rightDecorationStackPane.getChildren().add(newValue);
            rightDecorationNode = newValue;
        }
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset,
                                      double bottomInset, double leftInset) {
        double baseWidth = super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
        double leftDecorationWidth = (leftDecorationStackPane == null)
                ? 0.0
                : snapSize(leftDecorationStackPane.prefWidth(height));
        double rightDecorationWidth = (rightDecorationStackPane == null)
                ? 0.0
                : snapSize(rightDecorationStackPane.prefWidth(height));

        return baseWidth + leftDecorationWidth + rightDecorationWidth + leftInset + rightInset;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset,
                                       double bottomInset, double leftInset) {
        double baseHeight = super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
        double leftDecorationHeight = (leftDecorationStackPane == null)
                ? 0.0
                : snapSize(leftDecorationStackPane.prefHeight(- 1));
        double rightDecorationHeight = (rightDecorationStackPane == null)
                ? 0.0
                : snapSize(rightDecorationStackPane.prefHeight(- 1));

        return Math.max(baseHeight, Math.max(leftDecorationHeight, rightDecorationHeight));
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset,
                                     double bottomInset, double leftInset) {
        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    /*
     *     ╭─────────────────────────────────────────────────────────────────────────────╮
     *     │            ▩              Text                                ▩            │
     *     ╰─────────────────────────────────────────────────────────────────────────────╯
     *
     *     |<- leftDecorationWidth -> | <- textFieldWidth -> | <- rightDecorationWidth ->|
     *     |<-------------------------------- fullWidth -------------------------------->|
     *     ↑                          ↑                      ↑
     *     leftDecorationX            textFieldX             rightDecorationX
     */
    @Override
    protected void layoutChildren(final double x, final double y, final double w, final double h) {
        final double fullHeight = h + snappedTopInset() + snappedBottomInset();

        double leftDecorationWidth = (leftDecorationStackPane == null)
                ? 0.0
                : snapSize(leftDecorationStackPane.prefWidth(fullHeight));
        double rightDecorationWidth = (rightDecorationStackPane == null)
                ? 0.0
                : snapSize(rightDecorationStackPane.prefWidth(fullHeight));

        double textFieldX = snapPosition(x) + snapSize(leftDecorationWidth);
        double textFieldWidth = w - snapSize(leftDecorationWidth) - snapSize(rightDecorationWidth);
        super.layoutChildren(textFieldX, 0, textFieldWidth, fullHeight);

        if (leftDecorationStackPane != null) {
            double leftDecorationX = 0;
            leftDecorationStackPane.resizeRelocate(leftDecorationX, 0,
                    leftDecorationWidth, fullHeight);
        }
        if (rightDecorationStackPane != null) {
            double rightDecorationX = (rightDecorationStackPane == null)
                    ? 0.0
                    : (w - rightDecorationWidth) + snappedLeftInset();
            rightDecorationStackPane.resizeRelocate(rightDecorationX, 0,
                    rightDecorationWidth, fullHeight);
        }
    }
}
