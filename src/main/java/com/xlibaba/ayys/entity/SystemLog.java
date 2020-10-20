package com.sample;


import com.xlibaba.ayys.utils.FieldColName;

public class SystemLog {

  private long id;
  private java.sql.Timestamp time;
  private long type;
  private String content;
  @FieldColName(value = "person_id")
  private long personId;
  private String ip;
  @FieldColName(value = "is_delete")
  private long isDelete;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(long isDelete) {
    this.isDelete = isDelete;
  }

}
