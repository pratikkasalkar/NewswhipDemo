import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static List<String> taskList = new LinkedList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Available Options ->\n1. ADD<space><url><space><social score>\n2. REMOVE<space><url>\n3. Export");
        while (sc.hasNextLine()) {
            String read = sc.nextLine().toLowerCase().trim();
            if (read.toLowerCase().equals("exit")) {
                break;
            }
            if (isAddEntryValid(read) || isRemoveEntryValid(read) || read.equals("export")) {
                String operand = read.split(" ")[0];
                switch (operand) {
                    case "add":
                        taskList.add(read.split(" ")[1].toLowerCase() + " " + read.split(" ")[2].toLowerCase());
                        System.out.println("Entry Added!, List Size :" + taskList.size());
                        break;
                    case "remove":
                        if (remove(taskList, read.split(" ")[1])) {
                            System.out.println("Entry Removed!, List Size :"+taskList.size());
                        } else {
                            System.out.println("Entry not found!, List Size :"+taskList.size());
                        }
                        break;
                    case "export":
                        exportToCsv(taskList);
                        break;
                    default:
                        System.out.println("Invalid Request! Please Try -> ADD<space><url><space><social score> or REMOVE<space><url> or EXPORT");
                        break;
                }
            }else {
                System.out.println("Invalid Request! Please Try -> ADD<space><url><space><social score> or REMOVE<space><url> or EXPORT");
            }
        }
    }

    public static boolean remove(List<String> taskList, String removeElement){

        boolean isRemoved=taskList.removeIf(x-> x.contains(removeElement));
        return isRemoved;
    }
    public static String exportToCsv(List<String> taskList){
        HashMap<String, CustomPair> exportMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(https?|ftp|file)://www.(.*).(.*)+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher;
        String url=null;
        String domain;
        Integer score,counter;
        ListIterator<String> taskListIterator = taskList.listIterator();
        while(taskListIterator.hasNext()){
            url = taskListIterator.next();
            matcher = pattern.matcher(url.split(" ")[0]);
            if (matcher.matches()) {
                domain = matcher.group(2).split("/")[0];
                score = Integer.valueOf(url.split(" ")[1]);
                if(exportMap.containsKey(domain)){
                    exportMap.get(domain).setKey(exportMap.get(domain).getKey()+1);
                    exportMap.get(domain).setValue(score+exportMap.get(domain).getValue());
                }else{
                    exportMap.put(domain,new CustomPair(1,score));
                }
            }
            else{
                System.out.println("No Match Found!");
            }
        }
        System.out.println("---------------------------------");
        System.out.println("domain;urls;social_score");
        System.out.println(exportMap.toString().replace("{", "").replace("}", "").replace(",", "\n").replace("=", ";").replace(" ",""));
        System.out.println("---------------------------------");
        return "taskList in ";
    }

    public static boolean isAddEntryValid(String entry){
        String addPatternStr ="add\\s(\\b(https?|ftp|file)://)?[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]\\s[0-9]+";
        Pattern addPattern = Pattern.compile(addPatternStr);
        Matcher matcher = addPattern.matcher(entry);
        return matcher.matches();
    }

    public static boolean isRemoveEntryValid(String entry){
        String addPatternStr ="remove\\s(\\b(https?|ftp|file)://)?[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern addPattern = Pattern.compile(addPatternStr);
        Matcher matcher = addPattern.matcher(entry);
        return matcher.matches();
    }

}

class CustomPair{
   Integer key;
   Integer value;

    public CustomPair(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key +";"+ value;
    }

}
