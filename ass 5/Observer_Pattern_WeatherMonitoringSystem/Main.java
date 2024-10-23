import java.util.ArrayList;
import java.util.List;

class WeatherStation {
    private List<WeatherDisplay> displays = new ArrayList<>();
    private double temperature;
    private double humidity;
    private double pressure;

    public void addDisplay(WeatherDisplay display) {
        displays.add(display);
    }

    public void removeDisplay(WeatherDisplay display) {
        displays.remove(display);
    }

    public void setWeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyDisplays();
    }

    private void notifyDisplays() {
        for (WeatherDisplay display : displays) {
            display.update(temperature, humidity, pressure);
        }
    }
}

interface WeatherDisplay {
    void update(double temperature, double humidity, double pressure);
}

class CurrentConditionsDisplay implements WeatherDisplay {
    @Override
    public void update(double temperature, double humidity, double pressure) {
        System.out.println("Current Conditions: " + temperature + "°C, " + humidity + "% humidity");
    }
}

class StatisticsDisplay implements WeatherDisplay {
    @Override
    public void update(double temperature, double humidity, double pressure) {
        System.out.println("Weather Statistics: Temp - " + temperature + ", Humidity - " + humidity);
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        WeatherDisplay currentDisplay = new CurrentConditionsDisplay();
        WeatherDisplay statisticsDisplay = new StatisticsDisplay();

        station.addDisplay(currentDisplay);
        station.addDisplay(statisticsDisplay);

        station.setWeatherData(22.5, 65, 1013);
        station.setWeatherData(25.0, 60, 1012);
    }
}
