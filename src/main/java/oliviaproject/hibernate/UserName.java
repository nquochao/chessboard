package oliviaproject.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class UserName  extends DefaultEntity implements IEntity{

	@Column(unique = true)
	private String userName;
	private String email;
	private String phone;

	private String password;
    @OneToOne (fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ChessBoardPreference preference;

	public ChessBoardPreference getPreference() {
		return preference;
	}

	public void setPreference(ChessBoardPreference preference) {
		this.preference = preference;
	}

	private Boolean isConnected;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}



}
