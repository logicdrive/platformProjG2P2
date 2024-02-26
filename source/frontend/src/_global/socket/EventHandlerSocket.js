import { useEffect } from 'react';
import useWebSocket, { ReadyState } from "react-use-websocket"

const SubscribeMessageCreatedSocket = (notifiedEventStatus) => {
  let proxyUrl = `ws://${window.location.host}/socket/collectedData/eventHandler`
  if(window.location.host === "localhost:3000")
    proxyUrl = "ws://localhost:8093/socket/eventHandler" // localHost 단독 서비스 테스트용

  const { sendJsonMessage, lastJsonMessage, readyState } = useWebSocket(
    proxyUrl,
    {
      share: true,
      shouldReconnect: () => true,
    },
  )

  
  useEffect(() => {
      console.log(`[EFFECT] Notified created message status by socket: <event:${JSON.stringify(lastJsonMessage)}>`)
      if((lastJsonMessage !== null) && lastJsonMessage.eventName && lastJsonMessage.value)
        notifiedEventStatus(lastJsonMessage.eventName, ((lastJsonMessage.value) ? JSON.parse(lastJsonMessage.value) : {}))
      else if(lastJsonMessage !== null)
        console.log(`[EFFECT] Ignored Data: ${lastJsonMessage}`)
  }, [lastJsonMessage, notifiedEventStatus])


  useEffect(() => {
    const jwtToken = localStorage.getItem("jwtToken")
    const tokenPayload = JSON.parse(atob(jwtToken.split(".")[1]))
    const userId = Number(tokenPayload.sub)

    console.log(`[EFFECT] Send data by using socket: <proxyUrl: '${proxyUrl}', userId: ${userId}>`)
    sendJsonMessage({"userId": userId})
  }, [sendJsonMessage, proxyUrl])


  useEffect(() => {
    const currentReadyState = {
      [ReadyState.CONNECTING]: 'Connecting',
      [ReadyState.OPEN]: 'Open',
      [ReadyState.CLOSING]: 'Closing',
      [ReadyState.CLOSED]: 'Closed',
      [ReadyState.UNINSTANTIATED]: 'Uninstantiated',
    }[readyState]

    console.log(`[EFFECT] Ready state of socket: <currentReadyState: ${currentReadyState}>`)
  }, [readyState])
}

export default SubscribeMessageCreatedSocket;