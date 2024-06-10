class TestMaps{

    public static void main(String[] args){
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        for(String key: map.keySet()){
            System.out.println(key + " : " + map.get(key));
        }
    }
   
}