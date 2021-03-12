package com.lucenesearchecommerce.controller;

@RestController
@RequestMapping("/product/search")
public class ProductSearchController {
    
    @Autowired
    private ILuceneService service;
    /**
     * 
     * @param pageQuery
     * @return
     * @throws ParseException 
     * @throws IOException 
     */
    @PostMapping("/searchProduct")
    private ResultBean<PageQuery<Product>> searchProduct(@RequestBody PageQuery<Product> pageQuery) throws IOException, ParseException {
        PageQuery<Product> pageResult= service.searchProduct(pageQuery);
        return ResultUtil.success(pageResult);
    }
    
}

public class ResultUtil<T> {

    public static <T> ResultBean<T> success(T t){
        ResultEnum successEnum = ResultEnum.SUCCESS;
        return new ResultBean<T>(successEnum.getCode(),successEnum.getMsg(),t);
    }

    public static <T> ResultBean<T> success(){
        return success(null);
    }

    public static <T> ResultBean<T> error(ResultEnum Enum){
        ResultBean<T> result = new ResultBean<T>();
        result.setCode(Enum.getCode());
        result.setMsg(Enum.getMsg());
        result.setData(null);
        return result;
    }
}

public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Return code
     */
    private int code;
    /**
     * Return message
     */
    private String msg;
    /**
     * Return value
     */
    private T data;
    ...

public enum ResultEnum {
    UNKNOW_ERROR(-1, "Unknown Error"),
    SUCCESS (0,'Success')
    PASSWORD_ERROR (10001, "User name or password error"),
    PARAMETER_ERROR (10002, "parameter error");

    /**
     * Return code
     */
    private Integer code;
    /**
     * Return message
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }