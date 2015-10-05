package com.myjava.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.morningstar.oneteam.portfolioanalysis.util.transform.Transformer;

public class ListUtil {

    public static <T> boolean isValid(List<T> list) {
        return list != null && !list.isEmpty();
    }

    public static <T> List<T> copyUnmodifiableList(List<T> list) {
        if (list == null) {
            return null;
        }

        List<T> newList = new ArrayList<T>(list.size());
        for (T t : list) {
            newList.add(t);
        }

        return newList;
    }

    public static <T> List<T> toList(Collection<T> sets) {
        return new ArrayList<T>(sets);
    }

    public static <T> Set<T> toSet(Collection<T> list) {
        return new HashSet<T>(list);
    }

    public static <T1, T2> List<T1> mapKeyToList(Map<T1, T2> map) {
        return new ArrayList<T1>(map.keySet());
    }

    public static <T1, T2> List<T2> mapValueToList(Map<T1, T2> map) {
        return new ArrayList<T2>(map.values());
    }

    public static List<Double> toList(double[] array) {
        if (array == null) {
            return new ArrayList<Double>(0);
        }

        List<Double> list = new ArrayList<Double>(array.length);
        for (double element : array) {
            list.add(element);
        }

        return list;
    }

    public static <T> List<T> toList(T[] array) {
        if (array == null) {
            return new ArrayList<T>(0);
        }

        List<T> list = new ArrayList<T>(array.length);
        for (T element : array) {
            list.add(element);
        }

        return list;
    }

    public static double[] toDoubleArray(List<Double> list) {
        if (list == null) {
            return new double[0];
        }

        int count = list.size();
        double[] array = new double[count];
        for (int i = 0; i < count; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public static String[] toStringArray(List<String> list) {
        if (list == null) {
            return new String[0];
        }

        int count = list.size();
        String[] array = new String[count];
        for (int i = 0; i < count; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public static <T> List<T> copyFromPosition(List<T> list, Integer fromPos, Integer endPos) {
        if (list == null) {
            return new ArrayList<T>();
        }

        if (fromPos < 0) {
            fromPos = 0;
        }
        if (endPos >= list.size()) {
            endPos = list.size() - 1;
        }

        List<T> newList = new ArrayList<T>();
        for (int i = fromPos; i <= endPos; i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    public static <T> List<T> copy(List<T> list) {
        if (list == null) {
            return new ArrayList<T>();
        }

        return new ArrayList<T>(list);
    }

    public static <T> List<T> makeListByOneObject(T obj) {
        List<T> list = new ArrayList<T>();
        list.add(obj);

        return list;
    }

    public static boolean checkContain(List<String> secIDs, String secId) {
        boolean isContain = false;
        for (String tempSecId : secIDs) {
            if (secId.equals(tempSecId)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    public static List<Integer> getHighestOrLowest(List<Double> rankPosition, int numbers, boolean isHighest) {
        List<Integer> secIds = new ArrayList<Integer>();
        List<Integer> tmpIndex = new ArrayList<Integer>();
        double maxRank = Double.POSITIVE_INFINITY;
        double minRank = Double.NEGATIVE_INFINITY;

        while (true) {
            // search index which is biggest rank in the range of less than
            // maxRank
            // search index which is smallest rank in the range of greater than
            // minRank
            double loopRank = isHighest ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            for (int i = 0, ii = rankPosition.size(); i < ii; i++) {
                double curRank = rankPosition.get(i);
                if (Double.isNaN(curRank)) {
                    continue;
                }
                boolean flag = isHighest ? curRank < maxRank && curRank > loopRank : curRank > minRank
                        && curRank < loopRank;
                if (flag) {
                    loopRank = curRank;
                    tmpIndex.clear();
                    tmpIndex.add(i);
                } else if (curRank == loopRank) {
                    tmpIndex.add(i);
                }
            }

            if (tmpIndex.size() == 0) {
                break;
            } else {
                if (isHighest) {
                    maxRank = loopRank;
                } else {
                    minRank = loopRank;
                }

                secIds.addAll(tmpIndex);
                tmpIndex.clear();
                if (numbers == 0) {
                    continue;
                } else {
                    if (secIds.size() >= numbers) {
                        secIds = ListUtil.copyFromPosition(secIds, 0, numbers - 1);
                        break;
                    }
                }
            }
        }

        return secIds;
    }

    /*public static <From, To> List<To> transform(List<From> list, Transformer<From, To> transformer) {
        List<To> toList = new LinkedList<To>();
        for (From from : list) {
            toList.add(transformer.transform(from));
        }
        return toList;
    }*/

    public static <T> List<T> getNotDuplicate(List<T> targetList, List<T> list) {
        List<T> newList = new ArrayList<T>();
        for (T t : targetList) {
            boolean isExist = false;
            for (T t2 : list) {
                if (t.equals(t2)) {
                    isExist = true;
                }
            }
            if (!isExist) {
                newList.add(t);
            }
        }
        return newList;
    }

    /*public static <T> boolean isEquals(List<T> list1, List<T> list2) {
        if (list1 == list2) {
            return true;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            T t1 = list1.get(i);
            T t2 = list2.get(i);
            if (!ObjectUtils.isEquals(t1, t2)) {
                return false;
            }
        }
        return true;
    }*/

    public static String getShortKey(Map<String, String> map, String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            String value = String.valueOf(map.size() + 1);
            map.put(key, value);
            return value;
        }
    }
}
