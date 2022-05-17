package com.idknoo.mispi3help.validate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class RvalueValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        double r;
        try {
            r = (Double) o;
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage("В поле R должно быть число!"));
        }
        if (r < +0 || r > +6) {
            throw new ValidatorException(new FacesMessage("Значение должно быть в диапазоне (1...5)!!!"));
        }
    }
}
