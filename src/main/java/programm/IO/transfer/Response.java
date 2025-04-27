package programm.IO.transfer;

import java.util.List;

/**
 * Response - record, служащий для хранения данных ответа
 * @param message - возвращаемое сообщение
 * @param args - доп. информация
 */
public record Response(String message, String args) {}