package oliviaproject.ui.dashboard;

import javax.swing.Icon;

public interface DashBoardMenu_Fr {
	public interface MENUBAR {

		String PREFERENCES = "Preferences";
	public interface Preferences{
		String SCALE="Echelle";
		String Dashboard="Dashboard";
		String Pieces = "Pieces";
		String SelectedTileColor = "Selection";
		String DemoColor = "Demo";

		public interface Color{
			String Color="Color";
			String Color1="Color White";
			String Color2="Color Black";
			String OK = "OK";
			
		}
		public interface Selected{
			String Color="Color";
			String Color1="Selected";
			String Color2="Possible";
			String OK = "OK";
			
		}
		public interface Scale{
			
		}
	}
	
	}
}
