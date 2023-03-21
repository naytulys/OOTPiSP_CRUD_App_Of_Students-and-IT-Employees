package com.entities;

import com.annotations.LocalizedName;

@LocalizedName("Designer")
public class Designer extends Employee {
    public enum Designer_Skills {
        HTML,
        CSS,
        Illustrator,
        InDesign;
    }

    public enum Designer_Type {
        Game,
        WEB,
        Graphic;
    }
    private Designer_Skills designer_Skills;
    private Designer_Type designer_Type;

    @LocalizedName("Designer skills")
    public Designer_Skills getDesigner_Skills() {
        return designer_Skills;
    }

    @LocalizedName("Designer type")
    public Designer_Type getDesigner_Type() {
        return designer_Type;
    }

    public void setDesigner_Skills(Designer_Skills designer_Skills) {
        this.designer_Skills = designer_Skills;
    }

    public void setDesigner_Type(Designer_Type designer_Type) {
        this.designer_Type = designer_Type;
    }
}
