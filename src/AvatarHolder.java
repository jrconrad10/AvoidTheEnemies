// Jacob Conrad and Jack Handy, Final Project, May 5, 2021, AvatarHolder class.
// This class stores the current selected avatar.

public class AvatarHolder {

	private Avatar avatar;

	// AvatarHolder constructor
	public AvatarHolder(Avatar avatar) {
		this.avatar = avatar;
	}
	
	
	// Gets the current avatar
	public Avatar getAvatar() {
		return avatar;
	}
	
	// Sets the current avatar
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
}
