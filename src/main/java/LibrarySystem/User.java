package LibrarySystem;

    class User {
        private String username;
        private String password;
        private int id;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public int getId() {
            return id;
        }

        public boolean login(String username, String password, int id) {
            return this.username.equals(username) && this.password.equals(password);
        }

        public Book searchBook(String title) {
            // Implementation for searching book
            return null;
        }

        public boolean borrowBook(Book book) {
            // Implementation for borrowing book
            return false;
        }

        public boolean returnBook(Book book) {
            // Implementation for returning book
            return false;
        }

        public boolean subscribe() {
            // Implementation for subscribing
            return false;
        }
    }

