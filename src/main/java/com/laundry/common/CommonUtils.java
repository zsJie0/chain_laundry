package com.laundry.common;


import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共工具类
 */
public final class CommonUtils {

    /**
     * 全国省份id（）
     */
    public static final String PROV_ID_ALL = "111";

    /**
     * 全部地市id
     */
    public static final String CITY_ID_ALL = "-1";

    /**
     * 默认显示七天的数
     */
    public static final String DEFAULT_TIME = "7";

    /**
     * 北十省份id
     */
    public static final String PROV_ID_NORTH_10 = "112";

    /**
     * 南二十一省份id
     */
    public static final String PROV_ID_SOUTH_21 = "113";

    /**
     * 北京省份id
     */
    public static final String PROV_ID_BEIJING = "011";

    /**
     * 天津省份id
     */
    public static final String PROV_ID_TIANJIN = "013";

    /**
     * 上海省份id
     */
    public static final String PROV_ID_SHANGHAI = "031";

    /**
     * 重庆省份id
     */
    public static final String PROV_ID_CHONGQING = "083";

    /**
     * 小数点
     */
    public static final String POINT = ".";

    /**
     * 初始出库物资
     */
    public static final int START_NUMBER = 0;



    /**
     * 工具类，不允许new对象
     */
    private CommonUtils() {
    }

    /**
     * double转BigDecimal
     *
     * @param value double
     * @return BigDecimal
     */
    public static BigDecimal doubleToBigDecimal(double value) {
        return new BigDecimal(String.valueOf(value));
    }

    /**
     * 判断字符为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判定object对象为空
     *
     * @param object 对象
     * @return 判断结果
     */
    public static boolean isBlank(final Object object) {
        return null == object || "".equals(object);
    }

    /**
     * 判定object对象不为空
     *
     * @param object 对象
     * @return 判断结果
     */
    public static boolean isNotBlank(final Object object) {
        return null != object && !"".equals(object);
    }

    /**
     * 判定map中key对应的value为空，则赋给缺省值
     *
     * @param map   map对象
     * @param key   map对象的key
     * @param value map对象的value
     */
    public static void defaultValueOfMap(final Map map, final Object key, final Object value) {
        if (isBlank(map.get(key))) {
            map.put(key, value);
        }
    }

    /**
     * 判定key是否为空，空则赋给缺省值value，否则返回自身字符串
     *
     * @param key   map对象的key
     * @param value map对象的value
     */
    public static String defaultValueOfObject(final Object key, final String value) {
        return key == null ? value : key.toString();
    }

    public static String defaultValueOfObjects(final Object key, final String value) {
        return isEmpty(key) ? value : key.toString();
    }

    /**
     * 判断省分id是否为直辖市
     *
     * @param provId 省分id
     * @return 结果
     */
    public static boolean isMunicipality(String provId) {
        return provId.equals(PROV_ID_BEIJING) || provId.equals(PROV_ID_TIANJIN) || provId.equals(PROV_ID_SHANGHAI) || provId.equals(PROV_ID_CHONGQING);
    }

    /**
     * 去掉后面无用的零
     *
     * @param s
     * @return
     */
    public static String removeZero(String s) {
        if (s.indexOf(POINT) > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

    /**
     * 对象中是否包含元素
     *
     * @param obj     对象
     * @param element 元素
     * @return 是否包含
     */
    public static boolean contains(Object obj, Object element) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            if (element == null) {
                return false;
            }
            return ((String) obj).contains(element.toString());
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).contains(element);
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).values().contains(element);
        }

        if (obj instanceof Iterator) {
            Iterator<?> iter = (Iterator<?>) obj;
            while (iter.hasNext()) {
                Object o = iter.next();
                if (equals(o, element)) {
                    return true;
                }
            }
            return false;
        }
        if (obj instanceof Enumeration) {
            Enumeration<?> enumeration = (Enumeration<?>) obj;
            while (enumeration.hasMoreElements()) {
                Object o = enumeration.nextElement();
                if (equals(o, element)) {
                    return true;
                }
            }
            return false;
        }
        if (obj.getClass().isArray() == true) {
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                Object o = Array.get(obj, i);
                if (equals(o, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 对象是否为空
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (o.toString().equals("")) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较两个对象是否相等。<br>
     * 相同的条件有两个，满足其一即可：<br>
     * 1. obj1 == null && obj2 == null; 2. obj1.equals(obj2)
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否相等
     */
    public static boolean equals(Object obj1, Object obj2) {
        return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
    }

    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     *
     * @param cardNum 待检验的原始字符串
     * @return 返回是否包含
     */
    public static boolean judgeContainsStr(String cardNum) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }




    /**
     * 从List<Map<>>中取一个字段数据，放在list中返回
     *
     * @param paramList
     * @param key       map中对应的字段
     * @return
     */
    public static List<String> getId(List<Map<String, Object>> paramList, String key) {
        List<String> idList = new ArrayList<>();
        for (Map<String, Object> map : paramList) {
            idList.add(map.get(key).toString());
        }
        return idList;
    }

    /**
     * 处理数据
     *
     * @param dataList 查询数据结果列表
     * @param key      数据结果中的key，对应图表的横轴，图例等
     * @return 组装结果
     */
    public static Map<String, Map<String, Object>> dataProcess2Level(List<Map<String, Object>> dataList, String key) {
        //key为省份，value中map对象，key为指标id，value为map对象，key为数据类型，value为数据值
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> data : dataList) {
                if (null == data.get(key)) {
                    continue;
                }
                String dataKey = data.get(key).toString();
                resultMap.put(dataKey, data);
            }
        }
        return resultMap;
    }

    /**
     * 日期类型转换
     * yyyyMMdd -- yyyy-MM-dd
     */
    public static String dateTransformation(String date) {
        if(isEmpty(date)){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date formatDate = null;
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String formatNewDate = sdf2.format(formatDate);
        return formatNewDate;
    }

    /**
     * 日期类型转换
     * yyyy-MM-dd -> yyyyMMdd
     */
    public static String dateTransformation2(String date) {
        if(isEmpty(date)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = null;
        try {
            formatDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String formatNewDate = sdf2.format(formatDate);
        return formatNewDate;
    }

    /**
     * 类型转换(List<String> ---> List<Integer>)
     * @param StringList 要进行转换的list集合
     * @return
     */
    public static List<Integer> parseIntegersList(List<String> StringList){
        List<Integer> IntegersList = new ArrayList<>();
        //遍历String的list
        for(String data : StringList){
            if(CommonUtils.isEmpty(data)){
                break;
            }else {
                //类型转换
                Integer iData = Integer.parseInt(data);
                IntegersList.add(iData);
            }
        }
        return IntegersList;
    }

    /**
     * 删除字符串数组中指定值 / 删除数组中的元素包含指定值
     * @param str 要进行操作的字符串
     * @param element 要删除的元素
     * @return result 返回的结果
     */
    public static String[] deleteElement(String[] str,String element){
        String[] result = null;
        //如果数组不为空
        if(!isEmpty(str)){
            //将字符串数组转换成list集合
            List<String> asList = Arrays.asList(str);
            //迭代器实现类不支持remove()删除，多一步转化
//            list.remove(element);
            Iterator<String> it = asList.iterator();
            while (it.hasNext()){
                String next = it.next();
                //返回指定字符在字符串中第一次出现处的索引，如果字符串没有这样的字符，则返回-1
                if(!(next.indexOf(element) == -1)){
                    it.remove();
                }
            }
            result = new String[asList.size()];
            asList.toArray();

        }
        return result;
    }

    /**
     * 获取当前日期（yyyyMMdd形式）
     * @return
     */
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(date);

        return format;
    }
}
