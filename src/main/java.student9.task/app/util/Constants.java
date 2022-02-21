package app.util;

public final class Constants {
    public static final String QUERYFILTER ="queryFilter";
    public static final String QUERY ="query";
    public static StringBuilder makeQuery(String string){
        StringBuilder stringBuilder = new StringBuilder(String.valueOf("where name like :"));
        stringBuilder.append(string);
        return stringBuilder;
    }

}
