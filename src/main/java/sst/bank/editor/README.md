# BankBank Categories Editor

A JavaFX GUI application for managing the `categories.json` file in the BankBank project.

## Features

The Categories Editor provides a user-friendly interface to:

1. **Create New Categories** - Add new expense categories with custom names
2. **Manage Category Properties**:
   - Update category name
   - Change priority level
   - Mark categories as income or savings
3. **Set Monthly Budgets** - Define budget amounts for each of the 12 months
4. **Manage Criteria** - Add, view, and remove matching criteria for categories:
   - Define criteria values (e.g., account numbers, transaction details)
   - Select criteria field type (COUNTERPARTY_ACCOUNT, DETAIL, etc.)
   - Add optional comments to criteria
5. **Delete Categories** - Remove categories (with confirmation)
6. **Save Changes** - Export modified categories back to the JSON file

## Application Structure

### Main Components

#### `CategoriesEditorApp.java`
The main application class that launches the GUI. It:
- Loads the `categories.json` file on startup
- Initializes the data repository with existing categories
- Displays the FXML-based user interface

#### `CategoriesEditorController.java`
The FXML controller that handles all UI interactions and business logic:
- Category selection and viewing
- Category creation and deletion
- Budget management for 12 months
- Criteria list management (add/remove)
- File saving with proper JSON formatting

#### `categories_editor.fxml`
The JavaFX FXML file defining the user interface layout with:
- Category selection dropdown
- Category information panel (name, priority, type)
- Monthly budget input fields
- Criteria management section with list and add form
- Save button for persisting changes

## How to Use

### Starting the Application

In IntelliJ IDEA, run `sst.bank.editor.CategoriesEditorApp` as a regular Java
application. This class is a launcher (not a subclass of JavaFX `Application`),
so the JavaFX libraries already on the project classpath can be initialized
without configuring a separate JavaFX module path.

Or launch it from Maven:
```bash
mvn exec:java -Dexec.mainClass="sst.bank.editor.CategoriesEditorApp"
```

### Workflow

1. **Select a Category**: Use the dropdown at the top to select an existing category
2. **View/Edit Category Info**: The form will populate with:
   - Category name
   - Priority (numeric)
   - Income/Savings flags
   - Monthly budget values
   - Criteria list

3. **Modify Category**:
   - Edit the name or priority in the form
   - Toggle Income/Savings checkboxes
   - Click "✏️ Update Category" to save changes

4. **Manage Monthly Budget**:
   - Enter budget values in decimal format (e.g., -1500.50)
   - Use comma or period as decimal separator
   - Values are automatically parsed

5. **Manage Criteria**:
   - **View**: All criteria for the selected category appear in the list
   - **Add New**:
     - Enter the criteria value (account number, transaction detail, etc.)
     - Select the field type from the dropdown
     - Optionally add a comment for reference
     - Click "➕ Add Criteria"
   - **Remove**: Select a criteria from the list and click "🗑️ Remove Selected Criteria"

6. **Create New Category**:
   - Click "➕ New Category" button
   - Enter the category name in the dialog
   - The new category will be added with default values

7. **Delete Category**:
   - Select the category to delete
   - Click "🗑️ Delete Category"
   - Confirm the deletion in the dialog

8. **Save Changes**:
   - Click "💾 Save Categories to File" to persist all changes
   - A confirmation message will appear if successful

## Data Format

The application reads and writes JSON in the following format:

```json
[
  {
    "priority": 50,
    "name": "Category Name",
    "income": false,
    "savings": false,
    "criteria": [
      {
        "criteria": "BE36774229214981",
        "field": "COUNTERPARTY_ACCOUNT",
        "comment": "Optional comment"
      }
    ],
    "budget": [-1500.50, -1500.00, 0.0, ..., 0.0]
  }
]
```

## Criteria Field Types

Available field types for matching criteria:
- `COUNTERPARTY_ACCOUNT` - Bank account numbers
- `DETAIL` - Transaction details/descriptions
- `VALUE_DATE` - Specific dates (with availability periods)

## Technical Details

### Dependencies
- JavaFX 20.0.2 (controls, graphics, fxml)
- Google Gson 2.14.0
- Lombok 1.18.46
- Log4j2 2.26.1
- Guava 33.6.0

### Key Classes Used
- `sst.bank.model.Category` - Category data model
- `sst.bank.model.Criteria` - Criteria matching rules
- `sst.bank.model.repo.DataRepository` - Singleton data repository
- `sst.bank.main.BankBankConstants` - Configuration constants

### Error Handling
- Input validation for all form fields
- File I/O error handling with user feedback
- Confirmation dialogs for destructive operations
- Log output for debugging

## Notes

- The application uses the singleton `DataRepository` to manage categories in memory
- Changes are NOT automatically saved - you must click the "Save Categories to File" button
- Budget values should use numbers (periods or commas for decimals work)
- Priority is typically in the range 0-100, but any integer is accepted
- Categories are displayed in sorted order by name

## Building

The project uses Maven. To build the editor module:

```bash
mvn clean compile
mvn clean package
```

The compiled JAR will be available in the `target/` directory.

## Logging

The application logs to the configured Log4j2 output. Check `log4j2.xml` for logging configuration.

Important debug information includes:
- Category selection and updates
- Criteria additions/removals
- File save operations
- Any validation errors
