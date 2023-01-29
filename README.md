<a name="readme-top"></a>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Indisparte/TCPClient">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">project_title</h3>

  <p align="center">
    A starter project for your client app
    <br />
    <a href="https://github.com/Indisparte/TCPClient/wiki"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Indisparte/TCPClient">View Demo</a>
    ·
    <a href="https://github.com/Indisparte/TCPClient/issues">Report Bug</a>
    ·
    <a href="https://github.com/Indisparte/TCPCLient/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

The project represents a very simple Andorid app capable of interfacing with a remote server. It contains the basic code to implement any app that needs to communicate with a remote server.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

- Java
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to remedy data loss when the device is rotated and also for better fragment lifecycle management.
- [Jetpack Navigation Component](https://developer.android.com/guide/navigation) to simplify the navigation between fragments

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

> To get a local copy up and running follow these simple example steps.

### Prerequisites
- Android Studio
- Server IP address
- Server Port address

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Indisparte/TCPClient.git
   ```
3. Enter your Server information in `local.properties`
   ```gradle
    SERVER_ADDRESS=xxx.xxx.x.xx
    SERVER_PORT=xxxx
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

The project is implemented as a simple app that sends integers taken from user input and can receive the list of integers or the maximum integer hosted by the server.
So all the basic operations such as **sending**, **receiving a data** or a **list of data** are represented by the project.

_For more examples, please refer to the [Wiki](https://github.com/Indisparte/TCPClient/wiki)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/Indisparte/TCPClient/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* []()
* []()
* []()

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Indisparte/TCPClient.svg?style=for-the-badge
[contributors-url]: https://github.com/Indisparte/TCPClient/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Indisparte/TCPClient.svg?style=for-the-badge
[forks-url]: https://github.com/Indisparte/TCPClient/network/members
[stars-shield]: https://img.shields.io/github/stars/Indisparte/TCPClient.svg?style=for-the-badge
[stars-url]: https://github.com/Indisparte/TCPClient/stargazers
[issues-shield]: https://img.shields.io/github/issues/Indisparte/TCPClient.svg?style=for-the-badge
[issues-url]: https://github.com/Indisparte/TCPClient/issues
[license-shield]: https://img.shields.io/github/license/Indisparte/TCPClient.svg?style=for-the-badge
[license-url]: https://github.com/Indisparte/TCPClient/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/iamantoniodinuzzo
[product-screenshot]: images/screenshot.png
