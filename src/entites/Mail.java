package entites;


public class Mail {
    private String sender_id;
    private String reciever_id;
    private String message;
    private String date_msg;
    public Mail(String s_id,String r_id,String msg,String dt){
        sender_id=s_id;
        reciever_id=r_id;
        message=msg;
        date_msg=dt;
    }

  
    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

  
    public String getReciever_id() {
        return reciever_id;
    }

    /**
     * @param reciever_id the reciever_id to set
     */
    public void setReciever_id(String reciever_id) {
        this.reciever_id = reciever_id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the date_msg
     */
    public String getDate_msg() {
        return date_msg;
    }

    /**
     * @param date_msg the date_msg to set
     */
    public void setDate_msg(String date_msg) {
        this.date_msg = date_msg;
    }
}