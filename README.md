# News App - Android App

## About
This application displays news articles sourced from [News API](https://newsapi.org/register)

## Libraries

- **Jetpack Compose:** for building UI
- **Hilt:** for dependency injection
- **Coil:** for image loading
- **Retrofit:** for HTTP Request
- **Paging3:** for paging
- **Google Truth:** for unit test assertions 
- **Turbine:** for testing coroutine flow

Before running the project, make sure to set up your environment variables like API_KEY that you get from registered from [News API](https://newsapi.org/register) in the `local.properties` file as follows:

```env
baseUrl=https://newsapi.org/v2/
newsApiKey={{YOUR_API_KEY}}
```

## Features 
### 1. News Screen 
The News Screen consists of 5 sections:
- Toolbar: include with title and search button
- Categories Row: Display different news categories based on [News API - Top Headlines](https://newsapi.org/docs/endpoints/top-headlines)
- Top Headlines Section
- Popular Section
- News List by Category: Displays a list of news articles based on the selected category

<p float="left">
    <img src="/assets/news_screen_1.png" width="200">
    <img src="/assets/news_screen_2.png" width="200">
    <img src="/assets/news_screen_3.png" width="200">
</p>

### 2. Search Screen 
Allows users to search for articles from various sources based on server availability data.
<p float="left">
    <img src="/assets/search_screen.jpg" width="200">
</p>

### 3. Detail Screen
Displays the original article loaded into a WebView. Users can also share the news via the share button in the toolbar.
<p float="left">
    <img src="/assets/detail_screen.jpg" width="200">
    <img src="/assets/detail_share_screen.jpg" width="200">
</p>
