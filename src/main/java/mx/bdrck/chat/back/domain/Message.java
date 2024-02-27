package mx.bdrck.chat.back.domain;

import java.time.Instant;

public record Message(String userName, String text, Instant time) {
}
