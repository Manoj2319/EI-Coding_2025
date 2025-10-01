class Level2Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("software installation")) {
            System.out.println("Level 2 Support: Handling software installation issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Issue not handled: " + issue);
        }
    }
}
