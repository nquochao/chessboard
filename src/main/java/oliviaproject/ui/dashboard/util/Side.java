package oliviaproject.ui.dashboard.util;

public enum Side {
Black,White,None;

public static Side getOppositeSide(Side s) {
	switch(s) {
	case Black:{
		return White;
	}
	case White:{
		return Black;
	}
	}
	return Side.None;
}
}
