# XML reader Application

## Overview
A Spring Boot application to upload and manage product information via XML files, offering endpoints to retrieve all products or search for a specific product by name.

## Features
- Upload XML files with product data.
- Retrieve all products in JSON format.
- Search for products by name.

## How It Works
1. **Run the Application**: Start with `UploadXmlApplication`.
2. **Upload XML**: Navigate to `http://localhost:8080` and upload an XML file.
3. **View Products**: Use the provided links to see all products or search by name.

## Endpoints
- **POST /upload**: Uploads and processes an XML file.
- **GET /products**: Returns all products.
- **GET /product/{name}**: Returns product details by name.
