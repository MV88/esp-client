package org.esp.publisher.form;


import it.jrc.form.component.FormConstants;
import it.jrc.form.controller.EditorController;
import it.jrc.form.controller.EditorController.EditCompleteListener;
import it.jrc.persist.Dao;

import com.vaadin.data.Property;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * A combo box which can manage the items within it.
 * 
 * Deletions have not yet been implemented.
 * 
 * @author Will Temperley
 *
 * @param <T>
 */
public class EditableCombo<T> extends EditableField<T> {

    public EditableCombo(final Class<T> clazz, Dao dao) {
        
        /*
         * The selection widget
         */
        super(new ComboBox2<T>());

        this.clazz = clazz;
        this.dao = dao;

        /*
         * The window
         */
        w = new Window();
        w.setModal(true);
        w.setWidth("728px");
        w.setHeight("450px");

        encapsulatedField.setWidth(FormConstants.FIELD_DEFAULT_WIDTH);

        encapsulatedField.setImmediate(true);

        populateCombo();

        encapsulatedField.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                Object obj = event.getProperty().getValue();
                setValue((T) obj);
            }
        });
    }
    
    public ComboBox2 getEncapsulatedField() {
        return (ComboBox2)encapsulatedField;
    }

    @Override
    public void setEditor(EditorController<T> editor) {
        this.editor = editor;
        w.setContent(editor);

        /*
         * Decide what to do
         */
        editor.addEditCompleteListener(new EditCompleteListener<T>() {
            @Override
            public void onEditComplete(T entity) {

//                encapsulatedField.removeAllItems();
//                populateCombo();
                if (encapsulatedField.containsId(entity)) {
                    System.out.println("Contained already.");
                } else {
                    System.out.println("Adding.");
                    encapsulatedField.addItem(entity);
                    encapsulatedField.setValue(entity);
                }
                
                fireValueChange(false);

                UI.getCurrent().removeWindow(w);

            }
        });
    }
}
