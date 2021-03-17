
package br.edu.iff.ControledeVendas.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Utilizei para validar o nome de pessoa e de cidade coma mesma anotação
public class NomeValidator implements ConstraintValidator<NomeValidation, String>{
     @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.matches("[0-9]+")) return false;//nomes não podem conter números
        return true;
    }
    
}
