package project.yara_silva.Jp_capacitacao.dtos.request;

import project.yara_silva.Jp_capacitacao.enums.RoleEnum;

public record RegisterRequestDTO(String name, String email, String password, RoleEnum role) {
}
