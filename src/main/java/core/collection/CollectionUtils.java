package core.collection;

import core.stored.Worker;

public class CollectionUtils {
    public static boolean checkExist(Integer ID) {
        CollectionManager collectionManager = CollectionManager.getCollectionManager();
        for (Worker worker : collectionManager.getLinkedList()) {
            if (worker.getId().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    static String display(Worker worker) {
        String info = "";
        info = String.format("Id элемента: %s\n" +
                "Имя рабочего: %s\n" +
                "Координата x: %s\n" +
                "Координата y: %s\n" +
                "Дата создания: %s\n" +
                "Зарплата рабочего: %s\n" +
                "Дата и время начала: %s\n" +
                "Дата конца: %s\n" +
                "Статус: %s\n" +
                "Организация: %s\n\n", worker.getId(), worker.getName(), worker.getCoordinates().getX(),
                worker.getCoordinates().getY(), worker.getCreationDate(), worker.getSalary(), worker.getStartDate(),
                worker.getEndDate(), worker.getStatus(), (worker.getOrganization().getFullName() + " , " + worker.getOrganization().getOrganizationType() + " , " + worker.getOrganization().getOfficialAddress().getStreet() + ".\n\n")
        );

        return info;
    }
}
