package ui.control;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

public class CPDecoratedTextField extends TextField {

    protected final ObjectProperty<Node> leftDecoration;
    protected final ObjectProperty<Node> rightDecoration;

    public CPDecoratedTextField() {
        super();

        leftDecoration = new SimpleObjectProperty<>(this, "leftDecoration");
        rightDecoration = new SimpleObjectProperty<>(this, "rightDecoration");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CPDecoratedTextFieldSkin(this) {
            @Override
            public ObjectProperty<Node> leftDecorationProperty() {
                return leftDecoration;
            }

            @Override
            public ObjectProperty<Node> rightDecorationProperty() {
                return rightDecoration;
            }
        };
    }
}
