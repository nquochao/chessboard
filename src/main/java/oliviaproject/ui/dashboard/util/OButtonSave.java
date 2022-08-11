package oliviaproject.ui.dashboard.util;

import java.lang.annotation.Annotation;
import java.util.Iterator;

@button(name="save")
public class OButtonSave extends OButton{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

void init() {
	 button[] annotations = this.getClass().getDeclaredAnnotationsByType(button.class);
	 for(button b:annotations ) {
		 this.setLabel(b.name());
	 }
}
}
