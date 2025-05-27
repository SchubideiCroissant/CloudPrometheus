from flask import Flask, request
import json
import datetime
import winsound
import pygame

app = Flask(__name__)

@app.route("/alert", methods=["POST"])
def alert():
    winsound.Beep(400, 2000)
    pygame.mixer.init()
    pygame.mixer.music.load("mp3/alarm.wav")
    pygame.mixer.music.play()

    data = request.json
    time = datetime.datetime.now().strftime("%H:%M:%S")
    print("\n====================  ALERT EMPFANGEN ====================")
    print(f"🕒 Zeit: {time}")
    for a in data.get("alerts", []):
        print(f"Status: {a['status'].upper()}")
        print(f"Alert: {a['labels'].get('alertname')}")
        print(f"Instance: {a['labels'].get('instance')}")
        print(f"Beschreibung: {a['annotations'].get('description')}")
        print("-" * 60)
    print("============================================================\n")
        
    return "", 200

if __name__ == "__main__":
    """
    engine = pyttsx3.init()
    voices = engine.getProperty('voices')
    for index, voice in enumerate(voices):
        print(f"{index}: {voice.name} ({voice.languages})")

    engine.setProperty('rate', 100)
    engine.say("Achtung! Ein neuer Alarm ist ein gegangen.")
    engine.runAndWait()
    """


    app.run(port=5001)
