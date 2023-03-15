package com.aeca.email.controller.api.v2;

import com.aeca.email.domain.dto.base.AecaEmailCollectionResponse;
import com.aeca.email.domain.dto.base.AecaEmailErrorResponse;
import com.aeca.email.domain.dto.base.AecaEmailItemResponse;
import com.aeca.email.domain.dto.request.AecaEmailCreateDeliveryTemplateRequestDto;
import com.aeca.email.domain.dto.response.AecaEmailDeliveryTemplateResponseDto;
import com.aeca.email.domain.enumeration.dict.AecaEmailDeliveryTypeDictCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Интерфейс описания Api шаблонов рассылки
 *
 */
@RequestMapping(
        path = AecaEmailDeliveryTemplateApi.CONTROLLER_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(
        name = "Контроллер шаблонов рассылки",
        description = "API методов по работе с шаблонами рассылки"
)
public interface AecaEmailDeliveryTemplateApi {
    String CONTROLLER_PATH = "/api/v2/delivery-template";

    @Operation(
            summary = "Создание нового шаблона рассылки",
            description = "POST метод REST API создания нового шаблона рассылки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Шаблон рассылки успешно создан",
                            content = @Content(schema = @Schema(implementation = SwaggerWorkaround.CreateResponse.class))
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @PostMapping("/")
    ResponseEntity<AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto>> createDeliveryTemplate(@RequestBody AecaEmailCreateDeliveryTemplateRequestDto request);

    @Operation(
            summary = "Изменение шаблона рассылки",
            description = "PUT метод REST API изменения шаблона рассылки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Шаблона рассылки успешно изменен",
                            content = @Content(schema = @Schema(implementation = SwaggerWorkaround.EditResponse.class))
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @PutMapping("/{deliveryTemplateId}")
    ResponseEntity<AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto>> editDeliveryTemplate(
            @Parameter(
                    description = "Идентификатор шаблона рассылки типа Long",
                    example = "1",
                    required = true
            )
            @PathVariable("deliveryTemplateId") Long deliveryTemplateId,
            @RequestBody AecaEmailCreateDeliveryTemplateRequestDto request);

    @Operation(
            summary = "Удаление шаблона рассылки",
            description = "DELETE метод REST API удаления шаблона рассылки",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Шаблон рассылки успешно удален"
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @DeleteMapping("/{deliveryTemplateId}")
    ResponseEntity<Void> deleteDeliveryTemplate(
            @Parameter(
                    description = "Идентификатор шаблона рассылки типа Long",
                    example = "1",
                    required = true
            )
            @PathVariable("deliveryTemplateId") Long deliveryTemplateId);

    @Operation(
            summary = "Получение шаблонов рассылки по типу рассылки",
            description = "GET метод REST API получения шаблонов рассылки по типу рассылки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Шаблоны рассылки успешно получены",
                            content = @Content(schema = @Schema(implementation = SwaggerWorkaround.GetByTypeResponses.class))
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @GetMapping("/byType/{deliveryType}")
    ResponseEntity<AecaEmailCollectionResponse<AecaEmailDeliveryTemplateResponseDto>> getDeliveryTemplatesByDeliveryType(
            @Parameter(
                    description = "Тип рассылки",
                    example = "CERTIFICATE_EXPIRED",
                    required = true
            )
            @PathVariable("deliveryType") AecaEmailDeliveryTypeDictCodes deliveryType);

    @Operation(
            summary = "Активация шаблона рассылки",
            description = "PATCH метод REST API активации шаблона рассылки",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Шаблон рассылки активирован"
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @PatchMapping("/activate/{deliveryTemplateId}")
    ResponseEntity<Void> activateTemplate(
            @Parameter(
                    description = "Идентификатор шаблона рассылки типа Long",
                    example = "1",
                    required = true
            )
            @PathVariable("deliveryTemplateId") Long deliveryTemplateId);

    @Operation(
            summary = "Отключение шаблона рассылки",
            description = "PATCH метод REST API отключения шаблона рассылки",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Шаблон рассылки отключен"
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @PatchMapping("/deactivate/{deliveryTemplateId}")
    ResponseEntity<Void> deactivateTemplate(
            @Parameter(
                    description = "Идентификатор шаблона рассылки типа Long",
                    example = "1",
                    required = true
            )
            @PathVariable("deliveryTemplateId") Long deliveryTemplateId);

    @Operation(
            summary = "Получение шаблона рассылки по идентификатору",
            description = "GET метод REST API получения шаблона рассылки по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Шаблон рассылки успешно получен",
                            content = @Content(schema = @Schema(implementation = SwaggerWorkaround.GetResponse.class))
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Внутренняя ошибка",
                            content = @Content(schema = @Schema(implementation = AecaEmailErrorResponse.class))
                    )
            }
    )
    @GetMapping("/{deliveryTemplateId}")
    ResponseEntity<AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto>> getDeliveryTemplate(
            @Parameter(
                    description = "Идентификатор шаблона рассылки типа Long",
                    example = "1",
                    required = true
            )
            @PathVariable("deliveryTemplateId") Long deliveryTemplateId
    );

    class SwaggerWorkaround {
        static class CreateResponse extends AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto> {
        }
        static class EditResponse extends AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto> {
        }
        static class GetByTypeResponses extends AecaEmailCollectionResponse<AecaEmailDeliveryTemplateResponseDto> {
        }
        static class GetResponse extends AecaEmailItemResponse<AecaEmailDeliveryTemplateResponseDto> {
        }
    }
}
