package botirovka;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose which implementation to use");
        System.out.println("s - for implementation using the Stream Api");
        System.out.println("d - for implementation without using the Stream Api");
        List<Athlete> athletes = initializeAthletes();

        switch (scanner.next()) {
            case ("s") -> implementationWithStream(athletes);
            case ("d") -> implementationWithoutStream(athletes);
            default -> System.out.println("This option does not exist. Try again");
        }


    }

    private static void implementationWithoutStream(List<Athlete> athletes){
        System.out.println("TASK 1\n");
        firstTaskWithoutStream(athletes);
        System.out.println("\nTASK 2\n");
        secondTaskWithoutStream(athletes);
        System.out.println("\nTASK 3\n");
        thirdTaskWithoutStream(athletes);
        System.out.println("\nTASK 4\n");
        fourthTaskWithoutStream(athletes);
        System.out.println("\nTASK 5\n");
        System.out.println(fifthTaskWithoutStream(athletes));
        System.out.println("\nTASK 6\n");
        sixthTaskWithoutStream(athletes);
    }
    private static void implementationWithStream(List<Athlete> athletes){
        System.out.println("TASK 1\n");
        firstTaskWithStream(athletes);
        System.out.println("\nTASK 2\n");
        secondTaskWithStream(athletes);
        System.out.println("\nTASK 3\n");
        thirdTaskWithStream(athletes);
        System.out.println("\nTASK 4\n");
        fourthTaskWithStream(athletes);
        System.out.println("\nTASK 5\n");
        System.out.println(fifthTaskWithStream(athletes));
        System.out.println("\nTASK 6\n");
        sixthTaskWithStream(athletes);
    }


    private static void firstTaskWithoutStream(List<Athlete> athletes) {
        List<Athlete> multiSportAthletes = new ArrayList<>();
        List<Athlete> singleSportAthletes = new ArrayList<>();

        for (Athlete athleteI : athletes) {
            int counter = 0;
            for (Athlete athleteJ : athletes){
                if(Objects.equals(athleteI.getId(), athleteJ.getId())){
                    counter++;
                }
            }
            if(counter > 1){
                multiSportAthletes.add(athleteI);
            }
            else{
                singleSportAthletes.add(athleteI);
            }
        }

        multiSportAthletes.sort(Comparator.comparing(Athlete::getSurname));

        System.out.println("Спортсмени з кількома видами спорту:");
        for (Athlete athlete : multiSportAthletes) {
            System.out.println(athlete);
        }
        System.out.println("\nСпортсмени з одним видом спорту:");
        for (Athlete athlete : singleSportAthletes) {
            System.out.println(athlete);
        }
    }

    private static void firstTaskWithStream(List<Athlete> athletes) {
        Map<String, Long> athleteCountMap = athletes.stream()
                .collect(groupingBy(Athlete::getId, counting()));

        List<Athlete> multiSportAthletes = athletes.stream()
                .filter(athlete -> athleteCountMap.get(athlete.getId()) > 1)
                //.distinct()
                .sorted(Comparator.comparing(Athlete::getSurname))
                .toList();

        List<Athlete> singleSportAthletes = athletes.stream()
                .filter(athlete -> athleteCountMap.get(athlete.getId()) == 1)
                .toList();

        System.out.println("Спортсмени з кількома видами спорту:");
        multiSportAthletes.forEach(System.out::println);

        System.out.println("\nСпортсмени з одним видом спорту:");
        singleSportAthletes.forEach(System.out::println);
    }

    private static void secondTaskWithoutStream(List<Athlete> athletes) {
        HashMap<String, ArrayList<Athlete>> athletsBySport = AthletsBySportHashMap(athletes);
        athletsBySport.forEach((sport, athletesBySport) -> {
            System.out.println("Sport: " + sport);
            athletesBySport.forEach(System.out::println);
        });

    }


    private static void secondTaskWithStream(List<Athlete> athletes) {
        Map<String, List<Athlete>> athletesBySport = athletes.stream()
                .collect(Collectors.groupingBy(Athlete::getSport));
        athletesBySport.forEach((sport, athletesList) -> {
            System.out.println("Sport: " + sport);
            athletesList.forEach(System.out::println);
        });
    }
    private static void searchSportsman(List<Athlete> athletes, String surname){
        for (Athlete athlete: athletes) {
            if(athlete.getSurname().equals(surname)){
                System.out.println(athlete.getSport() + " " + athlete.getId());
            }
        }
    }

    private static void searchSportsmanStream(List<Athlete> athletes, String surname){
        Map<String, List<Athlete>> athletesBySurname =
                athletes.stream()
                        .collect(groupingBy(Athlete::getSurname));
        athletesBySurname.get(surname).forEach(athlete -> System.out.println(athlete.getId() + " " + athlete.getSport()));
    }

    private static HashMap<String, ArrayList<Athlete>> AthletsBySportHashMap(List<Athlete> athletes) {
        Set<String> sports = fifthTaskWithoutStream(athletes);
        HashMap<String,ArrayList<Athlete>> athletsBySport = new HashMap<>();
        for(String sport : sports){
            ArrayList<Athlete> currentSportAthlets = new ArrayList<>();
            for (Athlete athlete: athletes){
                if(Objects.equals(athlete.getSport(), sport)){
                    currentSportAthlets.add(athlete);
                }
            }
            athletsBySport.put(sport,currentSportAthlets);
        }
        return athletsBySport;
    }

    private static void thirdTaskWithoutStream(List<Athlete> athletes) {
        Set<String> sports = fifthTaskWithoutStream(athletes);
        HashMap<String,ArrayList<Athlete>> athletsBySport = new HashMap<>();
        for(String sport : sports){
            ArrayList<Athlete> currentSportAthlets = new ArrayList<>();
            for (Athlete athlete: athletes){
                if(Objects.equals(athlete.getSport(), sport) && athlete.getMedals() > 5){
                    currentSportAthlets.add(athlete);
                }
            }
            athletsBySport.put(sport,currentSportAthlets);
        }
        athletsBySport.forEach((sport, athletesBySport) -> {
            System.out.println("Sport: " + sport);
            athletesBySport.forEach(System.out::println);
        });
    }

    private static void thirdTaskWithStream(List<Athlete> athletes) {
        Map<String, List<Athlete>> athletesBySport = athletes.stream()
                .filter(athlete -> athlete.getMedals() >= 5)
                .collect(Collectors.groupingBy(Athlete::getSport));
        athletesBySport.forEach((sport, athletesList) -> {
            System.out.println("Sport: " + sport + "\nAthlets with more than 5 medals");
            athletesList.forEach(System.out::println);
        });
    }

    private static void fourthTaskWithoutStream(List<Athlete> athletes) {
        List<Athlete> sortedAthletes = new ArrayList<>(athletes);
        for (int i = 0; i < sortedAthletes.size() - 1; i++) {
            for (int j = 0; j < sortedAthletes.size() - i - 1; j++) {
                if (sortedAthletes.get(j).getName().compareTo(sortedAthletes.get(j + 1).getName()) > 0) {
                    Athlete temp = sortedAthletes.get(j);
                    sortedAthletes.set(j, sortedAthletes.get(j + 1));
                    sortedAthletes.set(j + 1, temp);
                }
            }
        }
        System.out.println("Sorted by Name");
        for(Athlete athlete: sortedAthletes){
            System.out.println(athlete);
        }

        for (int i = 0; i < sortedAthletes.size() - 1; i++) {
            for (int j = 0; j < sortedAthletes.size() - i - 1; j++) {
                if (sortedAthletes.get(j).getMedals() < sortedAthletes.get(j + 1).getMedals()) {
                    Athlete temp = sortedAthletes.get(j);
                    sortedAthletes.set(j, sortedAthletes.get(j + 1));
                    sortedAthletes.set(j + 1, temp);
                }
            }
        }
        System.out.println("Sorted by Medals");
        for(Athlete athlete: sortedAthletes){
            System.out.println(athlete);
        }
    }

    private static void fourthTaskWithStream(List<Athlete> athletes) {
        List<Athlete> sortedAthletes = new ArrayList<>(athletes);

        sortedAthletes.sort(Comparator.comparing(Athlete::getName));
        System.out.println("Sorted by Name");
        sortedAthletes.forEach(System.out::println);

        sortedAthletes.sort(Comparator.comparing(Athlete::getMedals).reversed());
        System.out.println("Sorted by Medals");
        sortedAthletes.forEach(System.out::println);
    }

    private static Set<String> fifthTaskWithoutStream(List<Athlete> athletes) {
        Set<String> sports = new HashSet<>();
        for (Athlete athlete: athletes) {
            sports.add(athlete.getSport());
        }
        return sports;
    }
    private static Set<String> fifthTaskWithStream(List<Athlete> athletes) {
        return athletes.stream()
                .map(Athlete::getSport)
                .collect(toSet());
    }

    private static void sixthTaskWithoutStream(List<Athlete> athletes) {
        Set<String> sports = fifthTaskWithoutStream(athletes);
        HashMap<String, ArrayList<Athlete>> athletsBySport = AthletsBySportHashMap(athletes);
        for (String sport: sports) {
            ArrayList<Athlete> bestAthletsBySpecificSport = new ArrayList<>();
            int max = 0;
            ArrayList<Athlete> athletsBySpecificSport = athletsBySport.get(sport);
            for (Athlete athlete: athletsBySpecificSport) {
                if(athlete.getMedals() > max){
                    max = athlete.getMedals();
                }
            }
            for (Athlete athlete: athletsBySpecificSport) {
                if(athlete.getMedals() == max && max != 0){
                    max = athlete.getMedals();
                    bestAthletsBySpecificSport.add(athlete);
                }
            }
            if(bestAthletsBySpecificSport.isEmpty()){
                System.out.println("Sport " + sport);
                System.out.println("There is no athlete with medals");
            }
            else{
                for (Athlete athlete: bestAthletsBySpecificSport) {
                    System.out.println("Sport " + sport + " best athlete:" );
                    System.out.println(athlete);
                }
            }
        }
    }
    private static void sixthTaskWithStream(List<Athlete> athletes) {
        Set<String> sports = fifthTaskWithStream(athletes);
        for (String sport: sports) {
            Optional<Athlete> topAthlete = athletes.stream()
                    .filter(athlete -> athlete.getSport().equals(sport))
                    .filter(athlete -> athlete.getMedals() > 0)
                    .max(Comparator.comparingInt(Athlete::getMedals));

            topAthlete.ifPresentOrElse(
                    athlete -> System.out.println("Sport " + sport + " best athlete: " + athlete),
                    () -> System.out.println("There is no athlete with medals in sport: " + sport)
            );
        }
    }

    private static List<Athlete> initializeAthletes() {
        return Arrays.asList(
                new Athlete("Michael", "Phelps", "Swimming", 23, 35),
                new Athlete("Usain", "Bolt", "Athletics", 8, 37),
                new Athlete("Simone", "Biles", "Gymnastics", 7, 26),
                new Athlete("Roger", "Federer", "Tennis", 20, 42),
                new Athlete("Serena", "Williams", "Tennis", 23, 42),
                new Athlete("Lionel", "Messi", "Football", 0, 36),
                new Athlete("Cristiano", "Ronaldo", "Football", 0, 38),
                new Athlete("LeBron", "James", "Basketball", 4, 39)
        );
    }

}