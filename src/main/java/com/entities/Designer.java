package com.entities;

import com.annotations.LocalizedName;

import java.io.Serializable;

@LocalizedName("Designer")
public class Designer extends Employee implements Serializable {
    public enum DesignerSkills {
        HTML,
        CSS,
        Illustrator,
        InDesign
    }

    public enum DesignerType {
        Game,
        WEB,
        Graphic
    }

    private DesignerSkills designerSkills;
    private DesignerType designerType;
    private Manager manager;
    
    @LocalizedName("Designer skills")
    public DesignerSkills getDesignerSkills() {
        return designerSkills;
    }

    @LocalizedName("Designer type")
    public DesignerType getDesignerType() {
        return designerType;
    }

    @LocalizedName("Manager")
    public Manager getManager() {
        return manager;
    }
    
    public void setDesignerSkills(DesignerSkills designerSkills) {
        this.designerSkills = designerSkills;
    }

    public void setDesignerType(DesignerType designerType) {
        this.designerType = designerType;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    public Designer(){

    }

    public Designer(String name, String surname, Sex sex, String email, double salary, int experience, Education education, DesignerSkills designerSkills, DesignerType designerType, Manager manager) {
        super(name, surname, sex, email, salary, experience, education);
        this.designerSkills = designerSkills;
        this.designerType = designerType;
        this.manager = manager;
    }
}
