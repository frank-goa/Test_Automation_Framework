# Selenium Grid Setup on macOS with Docker

## 1. Install Docker Desktop

- Download and install Docker Desktop for Mac:  
[https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)

- Start Docker Desktop after installation.

---

## 2. Pull Selenium Grid Images

For Apple Silicon (M1/M2), use `--platform=linux/amd64` for compatibility.

```sh
docker pull --platform=linux/amd64 selenium/hub:latest
docker pull --platform=linux/amd64 selenium/node-chrome:latest
docker pull --platform=linux/amd64 selenium/node-firefox:latest
docker pull --platform=linux/amd64 selenium/node-edge:latest
```
<hr></hr>

## 3. Create a Custom Docker Network
```shell
docker network create Grid
```


<hr></hr>

## 4. Start Selenium Hub on the Custom Network

```shell
docker run -d --network Grid -p 4442-4444:4442-4444 --name selenium-hub selenium/hub:latest
```

<hr></hr>
## 5. Start Browser Nodes (Chrome, Firefox, Edge)

Chrome Node
```shell
docker run -d --network Grid --name chrome-node \
  -e SE_EVENT_BUS_HOST=selenium-hub \
  -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
  -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
  selenium/node-chrome:latest
```
Firefox Node

```shell
docker run -d --network Grid --name firefox-node \
  -e SE_EVENT_BUS_HOST=selenium-hub \
  -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
  -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
  selenium/node-firefox:latest
```
Edge Node

```shell
docker run -d --network Grid --name edge-node \
  -e SE_EVENT_BUS_HOST=selenium-hub \
  -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
  -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
  selenium/node-edge:latest
```

<hr></hr>

## 6. Access Selenium Grid Console

Open your browser and go to:

http://localhost:4444/ui/

<hr></hr>

## 7. Troubleshooting

   Check logs for any node:
   - docker logs <container-name>

   Remove containers if needed:
   - docker rm -f <container-name>


<hr></hr>

## 8. Notes

All containers must be on the same Docker network (Grid).
Use the correct platform for Apple Silicon.
You can scale nodes by running more containers as needed.
