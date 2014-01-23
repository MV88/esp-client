package org.esp.publisher.form;

import java.util.Collection;
import java.util.List;

import org.esp.domain.blueprint.EcosystemServiceIndicator;
import org.jrc.form.FieldGroup;
import org.jrc.form.editor.SubmitPanel;
import org.jrc.ui.SimpleHtmlHeader;
import org.jrc.ui.SimpleHtmlLabel;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

public class ViewToRename extends VerticalLayout implements IEditorView<EcosystemServiceIndicator> {
    
    private Label status;
    private SubmitPanel submitPanel;
    private CssLayout mainPanel;

    public ViewToRename() {
        addStyleName("display-panel");
        addStyleName("display-panel-padded");
        setSizeFull();
        

        submitPanel = new SubmitPanel();
        addComponent(submitPanel);

        status = new Label("Status");
        addComponent(status);
        
        ProgressBar pb = new ProgressBar();
        addComponent(pb);
        pb.setWidth("100%");
        pb.setValue(0.5f);
        
        SimpleHtmlLabel spacer = new SimpleHtmlLabel("&nbsp;");
        addComponent(spacer);
        
        this.mainPanel = new CssLayout();
        addComponent(mainPanel);
        
        mainPanel.setSizeFull();
        setExpandRatio(mainPanel, 1);
    }
    
    public void setStatus(String statusMessage) {
        status.setCaption(statusMessage);
    }

    @Override
    public SubmitPanel getSubmitPanel() {
        return submitPanel;
    }

    @Override
    public void buildForm(List<FieldGroup<EcosystemServiceIndicator>> fields) {
        for (FieldGroup<EcosystemServiceIndicator> fieldGroupMeta : fields) {
            
            FormLayout formLayout = new FormLayout();
            BeanFieldGroup<EcosystemServiceIndicator> bfg = fieldGroupMeta.getFieldGroup();

//            if (fieldGroupMeta.getDescription() != null) {
                mainPanel.addComponent(new SimpleHtmlHeader(fieldGroupMeta.getLabel()));
//            }
                
            if (fieldGroupMeta instanceof ColourMapFieldGroup) {
                mainPanel.addComponent(((ColourMapFieldGroup)fieldGroupMeta).getContent());
            }

            Collection<Field<?>> fieldGroupFields = bfg.getFields();
            for (Field<?> field : fieldGroupFields) {
                mainPanel.addComponent(field);
            }
            mainPanel.addComponent(new SimpleHtmlLabel("&nbsp;"));
            // view.addComponent(formLayout, fieldGroupMeta.getLabel(),
            // fieldGroupMeta.getDescription());
//            addComponent(formLayout);
        }
    }
}
