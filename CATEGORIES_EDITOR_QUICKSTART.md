# Categories Editor - Quick Start Guide

## 📋 What Was Created

A complete **JavaFX GUI application** for editing the `categories.json` file with the following structure:

```
src/main/java/sst/bank/editor/
├── CategoriesEditorApp.java          (Main launcher - 79 lines)
├── CategoriesEditorController.java   (UI controller - 390 lines)
└── README.md                         (Detailed documentation)

src/main/resources/
└── categories_editor.fxml            (GUI layout - 152 lines)
```

## 🎯 Core Features

### ✅ Create New Categories
```
Click "➕ New Category" → Enter name → Category is created with defaults
```

### ✅ Add New Criteria to Categories
```
Select category → Fill in:
  • Value: "BE36774229214981" (account, transaction detail, etc.)
  • Field: Choose from dropdown (COUNTERPARTY_ACCOUNT, DETAIL, etc.)
  • Comment: "Optional bank name" (optional)
→ Click "➕ Add Criteria"
```

### ✅ Change Category Budget
```
Select category → Edit 12 monthly budget fields → Click "✏️ Update Category"
Format: decimal numbers (use . or , as decimal separator)
Example: -1500.50 or -1500,50
```

### ✅ Change Category Priority
```
Select category → Edit Priority field → Click "✏️ Update Category"
Default: 50 (any integer accepted)
```

## 🚀 How to Launch

### Using Maven:
```bash
cd c:\zt974\git\repo_perso\BankBank
mvn clean compile
mvn exec:java -Dexec.mainClass="sst.bank.editor.CategoriesEditorApp"
```

### Using IDE:
1. Open the project in IntelliJ IDEA, Eclipse, or VS Code
2. Navigate to `CategoriesEditorApp.java`
3. Right-click and select "Run"

### Using JAR (after build):
```bash
java -cp target/Bank-2026.002.jar sst.bank.editor.CategoriesEditorApp
```

## 💾 Important: Save Your Changes!

After making any modifications, **click the "💾 Save Categories to File" button** at the bottom to persist changes to `categories.json`.

## 🎨 User Interface Layout

```
┌─────────────────────────────────────────────────────┐
│  [Dropdown: Select Category] [➕ New Category]      │ ← Category Selection
├──────────────────┬──────────────────────────────────┤
│ LEFT PANEL       │ RIGHT PANEL                      │
│ ────────────     │ ─────────────────────────────    │
│ Category Info:   │ Criteria Management:             │
│ • Name           │ • Criteria List (scrollable)     │
│ • Priority       │ • Remove Criteria Button         │
│ • Income/Savings │ • Add New Criteria Form:         │
│ • Buttons        │   - Value input                  │
│                  │   - Field type dropdown          │
│ Monthly Budget:  │   - Comment (optional)           │
│ • 12 fields      │   - Add Button                   │
│   (Jan-Dec)      │                                  │
└──────────────────┴──────────────────────────────────┘
│  [💾 Save Categories to File]                       │ ← Save Button
└─────────────────────────────────────────────────────┘
```

## 📝 Typical Workflow

1. **Launch** the application
2. **Select** a category from the dropdown
3. **View** its current settings in the form
4. **Modify** as needed:
   - Edit name, priority, or flags
   - Adjust monthly budgets
   - Add/remove criteria
5. **Update** the category with the update button
6. **Save** all changes to the JSON file

## 🔧 Technical Details

### Files Created
- `CategoriesEditorApp.java` - Application entry point
- `CategoriesEditorController.java` - FXML controller with all logic
- `categories_editor.fxml` - JavaFX UI definition
- `README.md` - Detailed documentation

### Dependencies Added to pom.xml
- `javafx-fxml` (20.0.2)
- `javafx-controls` (20.0.2)
- `javafx-graphics` (20.0.2)

### Key Technologies
- **JavaFX** for GUI
- **Gson** for JSON handling
- **Lombok** for code generation
- **Log4j2** for logging

## ⚙️ Data Format

The application reads and writes this JSON format:
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
    "budget": [-1500.00, -1500.00, 0.0, ..., 0.0]
  }
]
```

## 🛡️ Safety Features

✓ **Input validation** - All form fields are validated
✓ **Confirmation dialogs** - Destructive actions require confirmation
✓ **Error handling** - User-friendly error messages
✓ **Logging** - All operations are logged for debugging

## 📚 Available Criteria Fields

- `COUNTERPARTY_ACCOUNT` - Bank account numbers
- `DETAIL` - Transaction details/descriptions
- `VALUE_DATE` - Specific dates with availability periods
- (And others defined in CriteriaField enum)

## ✨ Tips & Tricks

- **Budget Format**: Use either comma or period as decimal separator
- **Category Sorting**: Categories are automatically sorted alphabetically
- **Criteria Comments**: Use comments to remember what each criteria is for
- **Priority Levels**: Higher priority = evaluated first
- **Drag & Drop**: (Not yet implemented, but could be added)

## 📞 Troubleshooting

### Application won't start
- Ensure Java 18+ is installed
- Check that Maven dependencies are downloaded
- Verify `categories.json` exists in resources folder

### Changes not saving
- **Remember to click "💾 Save Categories to File"**
- Check that you have write permissions to the file
- Check logs for any IO errors

### Categories not loading
- Verify `categories.json` is in `src/main/resources/`
- Check the JSON format is valid
- Review application logs for parse errors

## 🔍 What's Inside

### CategoriesEditorApp.java (79 lines)
- Loads categories.json on startup
- Initializes JavaFX Stage
- Sets up FXML controller

### CategoriesEditorController.java (390 lines)
- **Budget management**: 12 month input fields
- **Category CRUD**: Create, read, update, delete operations
- **Criteria management**: Add/remove matching rules
- **Validation**: Input checking and error handling
- **File I/O**: JSON serialization with Gson
- **UI Updates**: Dynamic form population

### categories_editor.fxml (152 lines)
- BorderPane layout with top, center, bottom sections
- Split pane with left (category info) and right (criteria) panels
- TitledPanes for organization
- GridPanes for form layouts
- ListView for criteria display

---

**Created**: 2026-07-16  
**Version**: 1.0  
**Status**: Ready to use ✅
