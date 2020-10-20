package com.xlibaba.ayys.entity;

public class User {

  private long id;
  private String username;
  private String password;
  private String gender;
  private long phone;
  private String emial;
  private long addrId;
  private long isDelete;
  private long isUsed;
  private String remark;
  private String file;

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", gender='" + gender + '\'' +
            ", phone=" + phone +
            ", emial='" + emial + '\'' +
            ", addrId=" + addrId +
            ", isDelete=" + isDelete +
            ", isUsed=" + isUsed +
            ", remark='" + remark + '\'' +
            ", file='" + file + '\'' +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public long getPhone() {
    return phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }


  public String getEmial() {
    return emial;
  }

  public void setEmial(String emial) {
    this.emial = emial;
  }


  public long getAddrId() {
    return addrId;
  }

  public void setAddrId(long addrId) {
    this.addrId = addrId;
  }


  public long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(long isDelete) {
    this.isDelete = isDelete;
  }


  public long getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(long isUsed) {
    this.isUsed = isUsed;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

}
