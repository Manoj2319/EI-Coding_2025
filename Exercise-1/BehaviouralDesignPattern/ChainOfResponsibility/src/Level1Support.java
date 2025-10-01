class Level1Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("password reset")) {
            System.out.println("Level 1 Support: Handling password reset issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Issue not handled: " + issue);
        }
    }
}
