package primero;
// Generated 18-oct-2016 12:16:23 by Hibernate Tools 3.4.0.CR1

/**
 * DepartId generated by hbm2java
 */
public class DepartId implements java.io.Serializable {

	private byte deptNo;
	private String dnombre;
	private String loc;

	public DepartId() {
	}

	public DepartId(byte deptNo) {
		this.deptNo = deptNo;
	}

	public DepartId(byte deptNo, String dnombre, String loc) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
	}

	public byte getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(byte deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DepartId))
			return false;
		DepartId castOther = (DepartId) other;

		return (this.getDeptNo() == castOther.getDeptNo())
				&& ((this.getDnombre() == castOther.getDnombre()) || (this.getDnombre() != null
						&& castOther.getDnombre() != null && this.getDnombre().equals(castOther.getDnombre())))
				&& ((this.getLoc() == castOther.getLoc()) || (this.getLoc() != null && castOther.getLoc() != null
						&& this.getLoc().equals(castOther.getLoc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getDeptNo();
		result = 37 * result + (getDnombre() == null ? 0 : this.getDnombre().hashCode());
		result = 37 * result + (getLoc() == null ? 0 : this.getLoc().hashCode());
		return result;
	}

}
