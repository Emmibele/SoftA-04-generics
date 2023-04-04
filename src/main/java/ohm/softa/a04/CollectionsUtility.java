package ohm.softa.a04;

import java.util.Comparator;

public abstract class CollectionsUtility {

    private CollectionsUtility(){}

    public static <T> SimpleList<T> sort(SimpleList<T> list, Comparator<T> comparator){
        SimpleList<T> result;
        try{
            result = list.getClass().newInstance();
        } catch(InstantiationException | IllegalAccessException e){
            result = new SimpleListImpl<>();
        }
        
        T compSource = null;

        while(list.size() < 0){        
        
            for (T listElement : list) {
                if(compSource.equals(null) || comparator.compare(listElement, compSource) <= 0){
                    compSource = listElement;
                }
            }
            result.add(compSource);
            
            list.removeElement(compSource);
            compSource = null;
        }

        return result;
    }


}
