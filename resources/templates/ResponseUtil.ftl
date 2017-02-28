package ${packageName};

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {

    private ResponseUtil() {}

    public static final String MSG_403 = "{\"msg\" : \" 403 Forbidden\"}";

    public static final String MSG_401 = "{\"msg\" : \"401 unauthorized\"}";

    public static final String MSG_400 = "{\"msg\" : \" 400 Bad Request\"}";

    /**
     * 返回状态的键
     * */
    public static final String RS_KEY = "status";

    /**
     * 返回失败状态的json串
     * */
    public static final String FAIL_RS = "{\"" + RS_KEY + "\":\"false\"}";

    /**
     * 返回成功状态的json串
     * */
    public static final String SUCCESS_RS = "{\"" + RS_KEY + "\":\"true\"}";

    /**
     * 设置 response 的状态，内容类型，返回的信息
     * @throws IOException
     * */
    public static void setResponse(HttpServletResponse response, int status, String contentType, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");	// 默认为utf-8

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        writer.write(message);
        writer.close();
    }

    /**
     * 设置 response 的状态，内容类型默认为 "application/json"，返回的信息
     * */
    public static void setResponse(HttpServletResponse response, int status, String message) throws IOException {
        setResponse(response, status, MediaType.APPLICATION_JSON_VALUE, message);
    }

    /**
     * 设置禁止访问 403 的状态
     * @throws IOException
     * */
    public static void set403ToResponse(HttpServletResponse response) throws IOException {
        set403ToResponse(response, MSG_403);
    }

    /**
     * 设置禁止访问 403 的状态, 以及返回的信息
     * @throws IOException
     * */
    public static void set403ToResponse(HttpServletResponse response, String message) throws IOException {
        setResponse(response, HttpServletResponse.SC_FORBIDDEN, message);
    }

    /**
     * 设置禁止访问 401 的状态
     * @throws IOException
     * */
    public static void set401ToResponse(HttpServletResponse response) throws IOException {
        set401ToResponse(response, MSG_401);
    }

    /**
     * 设置禁止访问 401 的状态, 以及返回的信息
     * @throws IOException
     * */
    public static void set401ToResponse(HttpServletResponse response, String message) throws IOException {
        setResponse(response, HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    /**
     * 获取请求参数无效时的返回实体
     * */
    public static ResponseEntity<String> getResponseEntityWhenInvalidReqParams() {
        return new ResponseEntity<String>(MSG_400, HttpStatus.BAD_REQUEST);
    }

    /**
     * 获取返回用户请求的数据时的返回实体，对应Get，状态值为200
     * */
    public static ResponseEntity<String> getResEntityForGet(String data) {
        return new ResponseEntity<String>(data, HttpStatus.OK);
    }

    /**
     * 获取返回用户请求的数据时的返回实体，对应Get，状态值为200
     *  不返回其他的值，仅仅需要告诉前端该操作成功或失敗
     * */
    public static ResponseEntity<String> getResEntityForGet(boolean result) {
        return result ? getResEntityForGetWhenSuccess() : getResEntityForGetWhenFail();
    }

    /**
     * 获取返回用户请求的数据时的返回实体，对应Get，状态值为200
     *  不返回其他的值，仅仅需要告诉前端该操作成功
     * */
    public static ResponseEntity<String> getResEntityForGetWhenSuccess() {
        return new ResponseEntity<String>(SUCCESS_RS, HttpStatus.OK);
    }

    /**
     * 获取返回用户请求的数据时的返回实体，对应Get，状态值为200
     *  不返回其他的值，仅仅需要告诉前端该操作失败
     * */
    public static ResponseEntity<String> getResEntityForGetWhenFail() {
        return new ResponseEntity<String>(FAIL_RS, HttpStatus.OK);
    }

    /**
     * 获取返回用户请求的数据时的返回实体，对应Get，状态值为200
     *  返回一个空字符串
     * */
    public static ResponseEntity<String> getResEntityForGet() {
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    /**
     * 获取用户新增或修改数据时的返回实体，对应POST/PUT/PATCH，状态值为201
     * */
    public static ResponseEntity<String> getResEntityForPPP(String data) {
        return new ResponseEntity<String>(data, HttpStatus.CREATED);
    }

    /**
     * 获取用户新增或修改数据时的返回实体，对应POST/PUT/PATCH，状态值为201
     * */
    public static ResponseEntity<String> getResEntityForPPPAndJson(Map<String, Object> data) {
        if(data == null)
            return getResEntityForPPPWhenFail();
        return new ResponseEntity<String>(new JSONObject(data).toJSONString(), HttpStatus.CREATED);
    }

    /**
     * 获取用户新增或修改数据时的返回实体，对应POST/PUT/PATCH，状态值为201
     * 不返回其他的值，仅仅需要告诉前端该操作成功或失敗
     * */
    public static ResponseEntity<String> getResEntityForPPP(boolean result) {
        return result ? getResEntityForPPPWhenSuccess() : getResEntityForPPPWhenFail();
    }

    /**
     * 获取用户新增或修改数据时的返回实体，对应POST/PUT/PATCH，状态值为201
     * 不返回其他的值，仅仅需要告诉前端该操作成功
     * */
    public static ResponseEntity<String> getResEntityForPPPWhenSuccess() {
        return new ResponseEntity<String>(SUCCESS_RS, HttpStatus.CREATED);
    }

    /**
     * 获取用户新增或修改数据时的返回实体，对应POST/PUT/PATCH，状态值为201
     * 不返回其他的值，仅仅需要告诉前端该操作失败
     * */
    public static ResponseEntity<String> getResEntityForPPPWhenFail() {
        return new ResponseEntity<String>(FAIL_RS, HttpStatus.CREATED);
    }

    /**
     * 获取用户删除时的返回实体，对应 DELETE，状态值为204
     * */
    public static ResponseEntity<String> getResEntityForDel(String data) {
        return new ResponseEntity<String>(data, HttpStatus.NO_CONTENT);
    }

    /**
     * 获取用户删除时的返回实体，对应 DELETE，状态值为204
     * 不返回其他的值，仅仅需要告诉前端该操作或失敗
     * */
    public static ResponseEntity<String> getResEntityForDel(boolean result) {
        return result ? getResEntityForDelWhenSuccess() : getResEntityForDelWhenFail();
    }

    /**
     * 获取用户删除时的返回实体，对应 DELETE，状态值为204
     * 不返回其他的值，仅仅需要告诉前端该操作成功
     * */
    public static ResponseEntity<String> getResEntityForDelWhenSuccess() {
        return new ResponseEntity<String>(SUCCESS_RS, HttpStatus.NO_CONTENT);
    }

    /**
     * 获取用户删除时的返回实体，对应 DELETE，状态值为204
     * 不返回其他的值，仅仅需要告诉前端该操作失败
     * */
    public static ResponseEntity<String> getResEntityForDelWhenFail() {
        return new ResponseEntity<String>(FAIL_RS, HttpStatus.NO_CONTENT);
    }

    /**
     * 返回jsonarray的返回实体
     * */
    public static ResponseEntity<String> getResEntityForGetAndJsonArray(List<Map<String, Object>> datas) {
        if(datas == null)
            return ResponseUtil.getResEntityForGet();

        return getResEntityForGet(toJSONArray(datas).toJSONString());
    }

    /**
     * 将map返回为json
     * */
    public static ResponseEntity<String> getResEntityForGetAndJson(Map<String,Object> data) {
        if(data == null)
            return ResponseUtil.getResEntityForGet();

        return getResEntityForGet(new JSONObject(data).toJSONString());
    }

    /**
     * 获取下载文件时的响应实体
     * @throws UnsupportedEncodingException
     * */
    public static ResponseEntity<byte[]> getResEntityForDownload(String fileName, String type, byte[] out) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(parseMediaType(type));
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes(), "ISO-8859-1"));
        return new ResponseEntity<byte[]>(out, headers, HttpStatus.CREATED);
    }

    /**
     * 获取下载文件失败时的响应实体
     * */
    public static ResponseEntity<byte[]> getResEntityForDownloadWhenFail() {
        return new ResponseEntity<byte[]>(new byte[0], HttpStatus.NOT_FOUND);
    }

    private static MediaType parseMediaType(String mediaType) {
        if(StringUtil.isNullOrEmpty(mediaType))
            return MediaType.APPLICATION_OCTET_STREAM;

        return MediaType.parseMediaType(mediaType);
    }

    /**
     * 获取图片流的响应实体
     * @throws UnsupportedEncodingException
     * */
    public static ResponseEntity<byte[]> getResEntityForImage(byte[] imageOut) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageOut, headers, HttpStatus.OK);
    }

    /**
     * 将List<Map<String,Object>> 放入JSONArray
     * */
    public static JSONArray toJSONArray(List<Map<String, Object>> datas) {
        JSONArray ja = new JSONArray();

        for(Map<String, Object> data : datas)
            ja.add(new JSONObject(data));

        return ja;
    }
}












