# 30513214 Shop

`30513214_shop` is a Java Swing desktop shopping application backed by a
Microsoft Access database. It provides separate customer and staff workflows
for browsing products, managing accounts, processing orders, and maintaining
the product catalogue.

## Features

### Customers

- Register for an account and sign in.
- Browse clothing and footwear products.
- Add products to a basket and place orders.
- View order history and order details.
- Edit account details or delete an account.

### Staff

- Sign in through the staff portal.
- View customer orders.
- Add, edit, and delete products.
- Update product stock levels.

## Technology

- Java Swing
- Apache Ant / NetBeans project structure
- Microsoft Access (`.accdb`)
- UCanAccess 2.0.9.3 and its bundled JDBC dependencies

## Requirements

- JDK 24 or a compatible Java installation. The project is configured with
  `javac.source=24` and `javac.target=24`.
- Apache Ant

The required UCanAccess JAR files are already included in
`Libraries/UCanAccess-2.0.9.3-bin/`, so no dependency download is required for
the normal build.

## Getting started

Clone the repository and change into its root directory:

```bash
git clone https://github.com/obi-1n/30513214_shop.git
cd 30513214_shop
```

Keep the working directory at the repository root when running the application.
The database connection uses the relative path
`Data/AssessmentShopDB.accdb`.

### Build

```bash
ant clean jar
```

The compiled application is written to:

```text
dist/30513214_shop.jar
```

### Run from the source project

```bash
ant run
```

The application starts at `views.MainMenu`.

### Run the packaged application

The Ant build copies the required runtime libraries into `dist/lib`. To run the
packaged JAR, ensure that the `Data` directory is available from the process
working directory because the application resolves the Access database using a
relative path:

```bash
java -jar dist/30513214_shop.jar
```

If the database cannot be found when launching from another directory, start
the command from the repository root or place the required `Data` directory in
the working directory.

## Project structure

```text
.
├── Data/                              Access database files
├── Libraries/UCanAccess-2.0.9.3-bin/  JDBC driver and runtime libraries
├── src/models/                        Domain models and database operations
├── src/views/                         Swing forms and application screens
├── dist/                              Packaged JAR and runtime libraries
├── build.xml                          Apache Ant build file
└── nbproject/                         NetBeans project configuration
```

The primary application entry point is
[`src/views/MainMenu.java`](src/views/MainMenu.java).
Database access is centralized in
[`src/models/DBManager.java`](src/models/DBManager.java).

## Database

The application expects the main database at:

```text
Data/AssessmentShopDB.accdb
```

The repository also contains several database copies under `Data/`, which can
be useful for preserving or testing alternate database states. Back up the
database before making manual changes.

## Development

Open the project in NetBeans or IntelliJ IDEA, or build it from the command
line with Ant. The Swing form files (`.form`) are kept alongside their
generated Java view classes in `src/views/`.

Generated build output is stored in `build/` and `dist/`. IDE-specific files
are stored in `.idea/` and `nbproject/`; avoid committing newly generated
machine-specific settings.

## Troubleshooting

- **Database connection errors:** verify that
  `Data/AssessmentShopDB.accdb` exists and that the application is being run
  with the repository root as the working directory.
- **Missing JDBC classes:** build with the repository's Ant configuration so
  the libraries in `Libraries/UCanAccess-2.0.9.3-bin/` are included on the
  classpath.
- **Unsupported class version:** use JDK 24 or update the project source and
  target settings consistently.

## License

No license file is currently included in this repository. Add a license before
redistributing the application.
