Recipe Sharing App

Overview

The Recipe Sharing App is a Java-based platform designed for users to manage and share recipes, communicate through messages, and handle user permissions and interactions. The app features both regular and admin user roles, with robust management and notification systems.

Features

Controllers

	•	AppController: Manages reading and writing to the database.
	•	MessageController: Handles operations like retrieving all messages, fetching inbox messages, and sending various recipe-related messages.

Models

	•	Message: Creates message instances, checks for equality, formats messages, and retrieves message properties such as ID and creation time.
	•	MessagePublisher: Manages observers, adding or deleting them, and notifying them of changes.
	•	MessageRepository: Manages messages with functionalities to check equality, update, and iterate through messages.

Users

	•	User Types:
	•	AdminUser: Can create new admins, ban, or delete regular users.
	•	RegularUser: Can be banned or soft deleted by admins and can access the recipe menu.
	•	User Permissions: Admins have full control over user management, while regular users have recipe-related privileges.

Text Storage

	•	Stores all textual content used within the app.

Use Cases

	•	EntityManager: An abstract class implementing Serializable.
	•	Managers:
	•	MessageManager: Initializes and manages messages, recipe publishers, and user inboxes.
	•	RecipeManager: Handles recipe operations like adding ingredients or steps, renaming recipes, and rating.
	•	UserManager: Manages user lists, creates users, records login history, bans users, and handles user deletions.

Utility

	•	Includes helper classes for file processing, ID generation, and text validation.

View Components

	•	Panels: Includes UI components like InboxPanel for messages, LoginHistoryPanel for displaying login histories, and various other panels for user interactions.

How to Run

	•	Setup: Clone the repository and configure the project in an IDE like IntelliJ.
	•	Execution: Run Starter.java to launch the app.
