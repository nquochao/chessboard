package oliviaproject.hibernate.sql;

import org.springframework.stereotype.Repository;

import oliviaproject.hibernate.entities.ChessBoardPreference;
@Repository

public class ChessBoardPreferenceSQL extends DefaultSQL<ChessBoardPreference> {

	@Override
	public Class getName() {
		return getEntity(new ChessBoardPreference());
	}

}