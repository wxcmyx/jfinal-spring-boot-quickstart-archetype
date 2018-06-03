package ${package}.web.rest;

public class Result {
    /**
     * 状态码：0，成功，1，失败
     */
    private int code=0;
    /**
     * 状态信息：
     */
    private String message="";
    /**
     * 错误码
     */
    private String ercode="";
    /**
     * 错误信息
     */
    private String ermessage="";
    /**
     * 其他数据
     */
    private Object data;

    /**
     * 设置 404 错误
     * @return
     */
    public Result set404(){
        this.code=1;
        this.message="path not found";
        this.ercode="404";
        this.ermessage="path not found";
        return this;
    }

    /**
     * 设置 500 错误
     * @return
     */
    public Result set500(){
        this.code=1;
        this.message="Server Error";
        this.ercode="500";
        this.ermessage="Server Error";
        return this;
    }
    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErcode() {
        return ercode;
    }

    public Result setErcode(String ercode) {
        this.ercode = ercode;
        return this;
    }

    public String getErmessage() {
        return ermessage;
    }

    public Result setErmessage(String ermessage) {
        this.ermessage = ermessage;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

}
