from flask import Flask, request
import json
import datetime
import os
import pyttsx3


app = Flask(__name__)

@app.route("/alert", methods=["POST"])
def alert():
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
    # Optional: Sound abspielen (nur Windows)
    if os.name == 'nt':
        import winsound
        winsound.Beep(1000, 300)
        engine = pyttsx3.init()
        engine.say("Achtung! Ein Alert wurde ausgelöst.")
        engine.runAndWait()
    return "", 200

if __name__ == "__main__":
    app.run(port=5001)
