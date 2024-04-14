# JogaII - Yoga Learning and Progress Tracking App

JogaII is a mobile application designed for Android devices, aimed at helping users learn yoga asanas and track their progress. This version of the project builds upon the foundation of JogaI, enhancing it with modern technologies and architectural patterns.

<div align="center">
  <img src="https://github.com/jula1717/jogaI/assets/82888111/16e57a4a-94be-404b-84f9-589a6e8fbb87" alt="jogaI logo">
</div>

## Technologies Used:
 - Kotlin: The application is developed primarily in Kotlin, leveraging its concise syntax and advanced features for Android development.
 - MVVM Design Pattern: The Model-View-ViewModel architectural pattern is employed for better separation of concerns and improved maintainability.
 - LiveData: ViewModels utilize LiveData objects to efficiently manage and propagate changes in data to UI components.
 - Room Persistence Library: Data such as yoga asanas is stored using Room, a robust SQLite object mapping library, for seamless data management.
 - DataStore: User settings and preferences are stored using DataStore, offering a modern alternative to SharedPreferences with support for Kotlin coroutines.
 - Kotlin Flow and Coroutines: Flow and coroutines are utilized for asynchronous and reactive programming, enhancing the app's responsiveness and efficiency.
 - Navigation Component: Navigation between fragments is handled with the Navigation Component, providing a more intuitive and structured navigation flow.
 - Dagger Hilt: Dependency injection is implemented using Dagger Hilt, simplifying the management of dependencies and promoting modularization.
 - ViewBinding: Layouts are created using ViewBinding, a feature that generates binding classes for XML layouts, eliminating the need for findViewById() calls and improving type safety.

Yoga II aims to provide users with an immersive and seamless experience in learning and practicing yoga, incorporating modern technologies and best practices in Android development.

### 1) Explore Asanas - Main View
JogaII's AsanasFragment offers users a comprehensive interface for exploring yoga poses. Within this fragment, users can not only browse and sort poses based on various criteria but also search for specific poses by their Polish or Sanskrit names and by pose type. Furthermore, users have the option to hide poses they've already learned (marked as completed), allowing for a focused and organized practice session.

<div align="center">
  <table>
    <tr>
      <td><img src="https://github.com/jula1717/jogaII/blob/4f49542c2e8ac2699a0e60c205d9b0dc09ecf582/hide.gif?raw=true" alt="hide completed"></td>
      <td><img src="https://github.com/jula1717/jogaII/blob/4f49542c2e8ac2699a0e60c205d9b0dc09ecf582/search.gif?raw=true" alt="search"></td>
    </tr>
  </table>
</div>

### 2) Detailed Asana View
In JogaII's details fragment, users can access comprehensive information about a selected asana, including its type, name in Polish and Sanskrit, difficulty level, and whether it has been learned. They can also switch between a graphical representation and a textual description of the asana and mark it as learned directly from this fragment, a feature previously available only in the main asanas fragment.
<div align="center">
  <img src="https://github.com/jula1717/jogaII/blob/4f49542c2e8ac2699a0e60c205d9b0dc09ecf582/details.gif?raw=true" alt="details">
</div>

### 3) Achievements View
In JogaII's progress fragment, users can track their achievements in learning yoga poses. The app categorizes asanas by type, allowing users to monitor their progress by seeing the number of poses they've learned compared to the total for each type.

<div align="center">
  <img src="https://github.com/jula1717/jogaII/blob/4f49542c2e8ac2699a0e60c205d9b0dc09ecf582/progress.jpg?raw=true" alt="progress" width="40%">
</div>

## Installation
To install JogaI on your Android device and run it in Android Studio, follow these steps:

Clone the GitHub repository by running the following command in your terminal or command prompt:

```
git clone https://github.com/jula1717/jogaII.git
```
Open Android Studio and select "Open an existing Android Studio project."

Navigate to the location where you cloned the repository in step 1 and select the "jogaII" folder. Click "OK" to open the project.

Wait for Android Studio to sync the project and download any necessary dependencies.

Once the project is successfully synced, connect your Android device to your computer and ensure that USB debugging is enabled on your device.

In Android Studio, click the "Run" button (green triangle) or select "Run" from the "Run" menu.

Choose your connected Android device from the list of available devices and click "OK."

Android Studio will build and install the app on your device. Once the installation is complete, you can run JogaII on your Android device.
