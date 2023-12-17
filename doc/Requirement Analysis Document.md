# Requirement Analysis Document

## Chapter 1-User Scenario

Below, we will imagine the user's usage scenario and draw a user scenario diagram.

### 1.Homepage Case:

1. The homepage of the emoji pack platform is displayed on the user screen, which includes recommended popular emoji packs and search boxes.
2. A user is browsing the homepage, scrolling the screen, and seeing thumbnails of various emojis.

### 2.Search Case:

1. Users enter keywords such as "cat" in the search box, and the search results page displays multiple emojis related to "cat".
2. Users click on a search result to view detailed information.

### 3.Emoji Detail Page Case:

1. The user opens a detailed page of the emoji package, where they can see a large image, name, author, and related information of the emoji package.
2. Users can click the button to add emojis to their favorites or share them on social media.
   Upload emoji case:
3. Users may need to fill in relevant information such as the name, label, and author information when uploading their own created emojis.
4. During the upload process, users can preview the emoji package and choose whether to share it publicly.

### 4.Personal Favorites Use Case:

1. Users view their personal favorites, which include their favorite emojis.
2. Users can manage their favorites, add and remove emojis.

### 5.Social Sharing Case:

1. The user selects an emoji pack, clicks the share button, and shares it with friends or followers on social media platforms.
2. On social media, friends can view emojis and interact with users.

### 6.Emoji Creation Case:

1. Users use built-in tools or third-party applications to create their own emojis.
2. Users can add elements such as text, graphics, and stickers to personalize emojis.

### 7.Feedback and Support Case:

1. If users discover problems or have suggestions, they can contact the support team and fill out the feedback form.
2. The support team may respond to user questions or provide assistance.

### 8.Draw a Scene Diagram Below:

![img](assets\1702209398924-e7e86dc7-d63a-422b-aa3e-7d88c48f2171-1702265314622-8.png)

## Chapter 2-Class-Based Modeling

### 1.Class Diagram

 We isolated the nouns in the requirements, abstracted some classes, and defined the relevant properties and operations, drawing the following class diagram: 

![img](assets\1702209520271-a62f78c7-dbff-460f-a28f-68e522140dd8.png)

### 2.CRC Modeling

Next, we perform CRC modeling for each class to get the CRC model index card: 

<img src="assets\1702209536249-e887ad2a-41ec-446e-b137-4977916f1445.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209553744-7a0ca1ed-5036-4711-9534-e25355d3f5af.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209560727-1de6badf-0dc1-4ea2-84f9-0c31a04ae3f2.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209570923-7fa6d0a1-876c-433e-ac30-26b5ba2d3604.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209576723-e47d9729-28f2-42a7-a807-d83ae2b35c9d.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209584662-78ce3a89-981b-4057-aab7-9f158dbc0bc4.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209595884-a97b6360-6b25-4296-bef1-598887fb9fd7.png" alt="img" style="zoom:67%;" />

<img src="assets\1702209604382-561b5899-a56d-4b25-a687-530e42dc180d.png" alt="img" style="zoom:67%;" />

### 3.UML Diagrams

We utilize UML diagrams to describe the core requirements.

- Use UML activity diagrams to describe user register requirements: 

<img src="assets\1702209659469-19d3dc91-1790-4de3-a960-6d4515883738.png" alt="img" style="zoom:67%;" />

- Using UML activity diagrams to describe user requirements for searching for emojis:

<img src="assets\1702209674307-581faef4-cb5a-4c47-b275-a42ed943ebb7.png" alt="img" style="zoom:67%;" />

- Using UML Sequence Diagrams to Describe User Requirements for Browsing Emojis:

<img src="assets\1702209687142-b573830e-ea41-4cd3-9638-7453a933abf4.png" alt="img" style="zoom:67%;" />

- Use UML Sequence Diagrams to Describe User Requirements for Uploading Emojis:

<img src="assets\1702209701082-40fe6d29-3535-409a-aa41-4b0ad1ed8b82.png" alt="img" style="zoom:67%;" />

## Chapter 3-Behavioral Modeling

In this part we focus on the functional aspects of our system and explain how it responds to events, user interactions, and system states.

### 1. UML Activity Diagram

- We use UML activity diagram to help capture and communicate the dynamic behavior of our software system 

![img](assets\1702210484944-0e7401c2-5556-4b7d-9125-ab50c78dc2d6.png)

### 2.UML State Diagram

- We use UML state diagram to document the state transitions and how they affect the system's behavior. 

![img](assets\1702210517155-21f87650-2fe7-4490-959a-b29835bce110.png)

## Chapter 4-Non-functional Requirements

### 1.Performance Requirements:

1. Response time: The platform should respond within seconds after the user requests to ensure a smooth user experience.
2. Number of concurrent users: The platform should support simultaneous concurrent users to meet the needs of high traffic periods.
3. Stability: The platform should have high stability to minimize crashes and service unavailability time.
4. User growth: The platform should be able to accommodate future user growth and have the ability to expand horizontally.

### 2.Security Requirements:

1. Data encryption: Users' personal information and communication should be protected through encryption methods.
2. Identity verification: The user's identity should undergo valid identity verification to ensure that only legitimate users can upload and edit emojis.
3. Copyright compliance: The platform should comply with copyright regulations and ensure that the emojis uploaded by users do not infringe on the intellectual property rights of others.
4. Reporting mechanism: Provide a reporting mechanism to address inappropriate or illegal content.

![img](assets\1702210376269-227aeb85-3e04-4947-ac5a-cf09611bea05.png)