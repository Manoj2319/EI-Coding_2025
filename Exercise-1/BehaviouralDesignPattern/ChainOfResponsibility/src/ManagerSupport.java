class ManagerSupport extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        System.out.println("Manager Support: Handling escalated issue - " + issue);
    }
}
