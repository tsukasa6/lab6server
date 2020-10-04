package core.collection;

import core.stored.Worker;
import core.utils.IDGenerator;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private LinkedList<Worker> linkedList;
    private ZonedDateTime creationDate;
    private List res = new ArrayList();
    private static CollectionManager collectionManager;

    private CollectionManager() {}

    public static CollectionManager getCollectionManager() {
        if (collectionManager == null){
            collectionManager = new CollectionManager();
        }

        return collectionManager;
    }

    public void initList() {
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            creationDate = ZonedDateTime.now();
        }
    }

    public LinkedList<Worker> getLinkedList() {
        return linkedList;
    }

    public void add(Worker worker) {
        linkedList.add(worker);
    }

    public void addJsonObject(Worker worker) {
        worker.setId(IDGenerator.generateID(worker.getId()));
        linkedList.add(worker);
    }

    public String getInfo() {
        String info = "";
        info += "Тип коллекции: " + linkedList.getClass().getName() + ";\n";
        info += "Дата инициализации коллекции: " + creationDate + ";\n";
        info += "Количество элементов в коллекции: " + linkedList.size() + ".\n\n";

        return info;
    }

    public String show() {
        String info = linkedList.stream()
                .map(CollectionUtils::display)
                .collect(Collectors.joining("\n"));

        if (info.equals("")) {
            info = "Коллекция пуста.";
        }

        return info;
    }

    public void update(Worker workerToUpdate, Integer workerId) {
        linkedList.forEach(worker -> {
            if (worker.getId().equals(workerId)) {
                worker.setName(workerToUpdate.getName());
                worker.setCoordinates(workerToUpdate.getCoordinates());
                worker.setSalary(workerToUpdate.getSalary());
                worker.setStatus(workerToUpdate.getStatus());
                worker.setOrganization(workerToUpdate.getOrganization());
            }
        });
    }

    public void removeById(Integer workerId) {
        linkedList.forEach(worker -> {
            if (worker.getId().equals(workerId)) { linkedList.remove(worker); }
        });
    }

    public void clear() {
        linkedList.clear();
    }

    // Работает? ЕСЛИ НЕТ, ТО ПЕРЕДЕЛАТЬ!
    public String maxByCreationDate() {
        String info = linkedList.stream().max(Comparator.comparing(Worker::getCreationDate)).get().toString();
        return info;
    }

    public String countGreaterThanSalary(String salary) {
        int counter = (int) linkedList.stream().filter(worker -> worker.getSalary() > Long.parseLong(salary)).count();
        String info = String.valueOf(counter);
        return info;
    }

    public String printDescending() {
        String info = "";
        Iterator itr = linkedList.descendingIterator();
        while (itr.hasNext()) {
            info = (String) itr.next();
        }
        return info;
    }

    // Done?
    public void addIfMin(Worker worker) {
        linkedList.forEach(worker1 -> {
            if (worker1.getId().compareTo(worker.getId()) > 0) {
                linkedList.add(worker);
            }
        });
    }

    // Done?
    public String removeLast() {
        String info = "";
        linkedList.removeLast();
        return info;
    }

    // Done?
    public String reorder() {
        String info = " ";
        Collections.reverse(linkedList);
        return info;
    }

    // Done
    public void appendToList(Object o){
        res.add(o);
    }
}
